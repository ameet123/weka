package org.ameet.ml;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddCluster;
import weka.gui.explorer.ClustererAssignmentsPlotInstances;
import weka.gui.visualize.VisualizePanel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by ameet.chaubal on 6/14/2017.
 * basic flow for analysis
 */
public class WekaApp {
    public static void main(String[] args) throws Exception {
//        Instances data = processArff("iris.arff");
//        Classifier cModel = buildClassifier(data);
//        testModel(data, cModel);
//        Instance toModelInstance = data.firstInstance();
//        toModelInstance.setDataset(data);
//        useModel(cModel, toModelInstance);
        List<String> names = new ArrayList<String>() {
            {
//                add("country");
                add("beer_servings");
                add("spirit_servings");
                add("wine_servings");
                add("total_litres_of_pure_alcohol");
            }
        };
        generateArff("drinks.csv", names, 1, "C:\\Users\\ameet.chaubal\\Documents\\ML\\drinks.arff", true);
    }

    private static void generateArff(String file, List<String> attribNames, int dataArrayStartIndex, String outFile,
                                     boolean isHeader) throws Exception {
        List<String> text = FileUtil.getPathFileContent(file, isHeader);
//        System.out.println(text);

        System.out.println("File:" + file);
        String relation = file.split("\\.")[0];
        ArrayList<Attribute> attributes = ArffUtil.getNumericAttributes(attribNames);
        Instances data = new Instances(relation, attributes, 0);

        // fill data
        for (String line : text) {
            String[] fields = ArffUtil.lineToArray(line, dataArrayStartIndex);
            double[] vals = ArffUtil.packData(data, fields);
            data.add(new DenseInstance(1.0, vals));
        }

        SimpleKMeans clusterer;
        // randomize data
        Random rand = new Random();
        Instances randData = new Instances(data);
        randData.randomize(rand);
        ClusterEvaluation eval = new ClusterEvaluation();
        int folds = 5;
        Instances predictedData = null;
        for (int n = 0; n < folds; n++) {
            Instances train = randData.trainCV(folds, n);
            Instances test = randData.testCV(folds, n);
            clusterer = ClusterUtil.getSimpleKMeans(train, "100");
            eval.setClusterer(clusterer);
            eval.evaluateClusterer(test);
            // pred
            AddCluster filter = new AddCluster();
            filter.setClusterer(clusterer);
            filter.setInputFormat(train);

            Filter.useFilter(train, filter);  // trains the classifier
            Instances pred = Filter.useFilter(test, filter);  // perform predictions on test set
            if (predictedData == null)
                predictedData = new Instances(pred, 0);
            for (int j = 0; j < pred.numInstances(); j++)
                predictedData.add(pred.instance(j));

            System.out.println(predictedData);
        }
        // Evaluate

        // build clusterer
//        eval.setClusterer(clusterer);
//        eval.evaluateClusterer(data);

        ClusterUtil.printCluster(eval);

        // Visualization
        // setup visualization
        // taken from: ClustererPanel.startClusterer()
//        ClustererAssignmentsPlotInstances plotInstances = new ClustererAssignmentsPlotInstances();
//        plotInstances.setClusterer(clusterer);
//        plotInstances.setInstances(data);
//        plotInstances.setClusterEvaluation(eval);
//        plotInstances.setUp();
//        String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
//        String cname = clusterer.getClass().getName();
//        if (cname.startsWith("weka.clusterers."))
//            name += cname.substring("weka.clusterers.".length());
//        else
//            name += cname;
//        name = name + " (" + data.relationName() + ")";
//        VisualizePanel vp = null;
//        try {
//            vp = new VisualizePanel();
//        } catch (Exception e) {
//            //
//        }
//        vp.setName(name);
//        vp.addPlot(plotInstances.getPlotData(cname));
//        JFrame jf = new JFrame("Weka Clusterer Visualize: " + vp.getName());
//        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jf.setSize(500, 400);
//        jf.getContentPane().setLayout(new BorderLayout());
//        jf.getContentPane().add(vp, BorderLayout.CENTER);
//        jf.setVisible(true);
////        System.out.println(data);
//        FileUtil.writeFile(outFile, data.toString());
    }


    private static Instances processArff(String file) throws IOException {
        InputStream in = WekaApp.class.getClassLoader().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ArffLoader.ArffReader arff = new ArffLoader.ArffReader(reader);
        Instances data = arff.getData();
        System.out.println("Class index==" + (data.numAttributes() - 1));
        data.setClassIndex(data.numAttributes() - 1);
        return data;

    }

    private static Classifier buildClassifier(Instances data) throws Exception {
        Classifier cModel = new NaiveBayes();
        cModel.buildClassifier(data);
        return cModel;
    }

    private static void testModel(Instances data, Classifier cModel) throws Exception {
        Evaluation eTest = new Evaluation(data);
        eTest.evaluateModel(cModel, data);
        String strSummary = eTest.toSummaryString();
        System.out.println(strSummary);
        double[][] cmMatrix = eTest.confusionMatrix();
        for (double[] row : cmMatrix) {
            Arrays.stream(row).forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }

    private static void useModel(Classifier cModel, Instance data) throws Exception {
        System.out.println("Testing==>" + data);
        double[] fDistribution = cModel.distributionForInstance(data);
        System.out.println(Arrays.toString(fDistribution));
    }
}
