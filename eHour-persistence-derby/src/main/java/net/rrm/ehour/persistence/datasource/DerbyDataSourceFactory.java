package net.rrm.ehour.persistence.datasource;

import net.rrm.ehour.appconfig.EhourHomeUtil;
import net.rrm.ehour.persistence.dbvalidator.DerbyDbValidator;
import net.rrm.ehour.util.IoUtil;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author thies
 */
public class DerbyDataSourceFactory {
    /**
     * Create datasource and validate database
     */
    public DataSource createDataSource(String databaseName) throws IOException {
        EmbeddedConnectionPoolDataSource dataSource = new EmbeddedConnectionPoolDataSource();
        dataSource.setDatabaseName(databaseName);

        File ehourPropertiesFile = EhourHomeUtil.getEhourPropertiesFile();

        Properties properties = new Properties();

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(ehourPropertiesFile);
            properties.load(stream);
            DerbyDbValidator validator = new DerbyDbValidator(properties.getProperty("ehour.db.version"), dataSource);
            validator.checkDatabaseState();

            return dataSource;
        } finally {
            IoUtil.close(stream);

        }
    }
}
