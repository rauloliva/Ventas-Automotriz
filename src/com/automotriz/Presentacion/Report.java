package com.automotriz.Presentacion;

import java.sql.Connection;
import com.automotriz.logger.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.view.JasperViewer;

public class Report {

    private String reportName;
    private Connection cnn;
    private final String reportDir = "Reports/";

    /**
     * @param reportName Specify the report name that is in the 'Reports'
     * directory & related to the SQL table
     */
    public Report(String reportName, Connection cnn) {
        this.reportName = reportName;
        this.cnn = cnn;
    }

    /**
     * Load the report file from 'Reports' directory, generating a view for the
     * user with the latest updates from the database table
     */
    public void generateReport() {
        try {
            Logger.log("Creating the " + reportName + " report...");
            String reportPath = reportDir + reportName + ".jrxml";
            JasperReport jr = JasperCompileManager.compileReport(reportPath);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, this.cnn);
            JasperViewer.viewReport(jp,false);
            Logger.log("Success on creating the " + reportName + " report");
        } catch (Exception ex) {
            Logger.error(ex.getMessage());
            Logger.error(ex.getStackTrace());
        } finally {
            try {
                this.cnn.close();
            } catch (Exception e) {
                Logger.error(e.getMessage());
                Logger.error(e.getStackTrace());
            }
        }
    }
}
