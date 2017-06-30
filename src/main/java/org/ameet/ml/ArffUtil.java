package org.ameet.ml;

import weka.core.Attribute;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ameet.chaubal on 6/30/2017.
 */
public class ArffUtil {
    public static ArrayList<Attribute> getNumericAttributes(List<String> attribNames) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        for (String name : attribNames) {
            attributes.add(new Attribute(name));
        }
        return attributes;
    }

    public static String[] lineToArray(String line, int dataArrayStartIndex) {
        String[] strings = line.split(",");
        if (dataArrayStartIndex > 0) {
            return Arrays.asList(line.split(",")).subList(dataArrayStartIndex, strings.length).toArray(new String[]{});
        } else {
            return strings;
        }
    }

    public static double[] packData(Instances data, String[] fields) {
        if (data.numAttributes() != fields.length) {
            throw new IllegalArgumentException("ERR: number of fields in instances should equal the data fields " +
                    "array: #data= " + data.numAttributes() + " #fields= " + fields.length);
        }
        double[] vals = new double[data.numAttributes()];
        for (int j = 0; j < fields.length; j++) {
            vals[j] = Double.valueOf(fields[j].trim());
        }
        return vals;
    }
}
