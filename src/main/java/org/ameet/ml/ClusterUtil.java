package org.ameet.ml;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 * Created by ameet.chaubal on 6/30/2017.
 */
public class ClusterUtil {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClusterUtil.class);

    public static SimpleKMeans getSimpleKMeans(Instances data, String maxIterations) {
        String[] options = new String[2];
        options[0] = "-I";
        options[1] = maxIterations;
        SimpleKMeans clusterer = new SimpleKMeans();   // new instance of clusterer
        try {
            clusterer.setOptions(options);
            clusterer.buildClusterer(data);
        } catch (Exception e) {
            System.out.println("Err: building options for clusterer " + e.getMessage());
            return null;
        }
        return clusterer;
    }

    public static void printCluster(   ClusterEvaluation eval) {
        LOGGER.debug("# of clusters: {} " , eval.getNumClusters());
       LOGGER.debug(eval.clusterResultsToString());
    }
}
