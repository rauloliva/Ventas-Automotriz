package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.io.*;
import java.util.*;

public class Excel {

    private final String url;
    private String[] header;
    private List<String[][]> data;

    /**
     * Creates a new object for exporting a CSV file
     *
     * @param url where the CSV file will be located
     */
    public Excel(String url) {
        this.url = url;
    }

    /**
     *
     * @param header
     */
    public void setHeader(String[] header) {
        this.header = header;
    }

    /**
     *
     * @param data
     */
    public void setData(List<String[][]> data) {
        this.data = data;
    }

    /**
     *
     * @return whether the file was created successfully
     */
    public boolean writeCSV() {
        try {
            if (url.equals("")) {
                return false;
            }

            FileWriter fw = new FileWriter(url);
            fw.write(String.join(",", header) + "\n");
            int i = 0;
            if (data != null) {
                for (String[] row : data.get(i)) {
                    fw.write(String.join(",", row) + "\n");
                    i++;
                }
            }
            fw.write("\n" + ReadProperties.props.getProperty("csv.importante"));
            fw.close();
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return false;
        }
    }

    public String[] readCSV() {
        try {
            if (url.equals("")) {
                return null;
            }
            String data[] = null;
            BufferedReader buffer = new BufferedReader(new FileReader(url));
            String line;
            boolean isHeader = true;
            while ((line = buffer.readLine()) != null) {
                if (isHeader) { //Skiping the header
                    isHeader = false;
                    continue;
                }
                //skip the CSV's information
                if (line.contains("IMPORTANTE")) {
                    continue;
                }
                data = line.split(",");
            }
            return data;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return null;
        }
    }
}
