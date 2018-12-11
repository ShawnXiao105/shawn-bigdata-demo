package com.cmhk;


import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException, ServiceException {
        new App().connect();
    }

    private void connect() throws IOException, ServiceException {
        Configuration config = HBaseConfiguration.create();

        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();

        config.addResource(new Path(path));

        try {
            HBaseAdmin.available(config);
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
            return;
        }

        HBaseClientOperations HBaseClientOperations = new HBaseClientOperations();
        HBaseClientOperations.run(config);
    }
}
