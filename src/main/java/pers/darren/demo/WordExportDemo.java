package pers.darren.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import pers.darren.demo.common.ConfigSingletonDL;

public class WordExportDemo {

    public static void main(final String[] args) throws Exception {
        final Map<String, String> dataModel = new HashMap<>();
        dataModel.put("ProjectCode", "20210115001");
        dataModel.put("ProjectName", "测试工程");
        dataModel.put("DesignUnit", "特能博世科技有限公司");
        dataModel.put("DesignStage", "已完成");
        dataModel.put("District", "湖南");
        dataModel.put("Designer", "包脉");
        dataModel.put("AuditTime", "2021年1月15日10时0分");

        final Configuration configuration = ConfigSingletonDL.getPropConfiguration();
        final Template template = configuration.getTemplate("project_audit_report.ftl", "UTF-8");
        final BufferedWriter writer = new BufferedWriter(new FileWriter("/home/darren/Desktop/project_audit_report.docx"));
        template.process(dataModel, writer);
        writer.flush();
        writer.close();
    }
}