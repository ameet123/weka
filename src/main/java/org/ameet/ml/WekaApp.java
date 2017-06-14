package org.ameet.ml;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by ameet.chaubal on 6/14/2017.
 * basic flow for analysis
 */
public class WekaApp {
    public static void main(String[] args) throws Exception {
        Instances data = processArff("iris.arff");
        Classifier cModel = buildClassifier(data);
        testModel(data, cModel);
        Instance toModelInstance = data.firstInstance();
        toModelInstance.setDataset(data);
        useModel(cModel, toModelInstance);
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
