package com.jm.springboottemplate.common.util;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Description: Project Property Utils.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 18:02
 **/
public class ProjectPropertyUtils {
    /**
     * Get model of project property
     *
     * @return
     */
    private static Model getModel() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            return reader.read(new FileReader("pom.xml"));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the version number of project
     *
     * @return Version number
     */
    public static String getVersion() {
        Model model = getModel();
        assert model != null;
        return model.getVersion();
    }

    /**
     * Get the artifact id of project
     *
     * @return Artifact id
     */
    public static String getArtifactId() {
        Model model = getModel();
        assert model != null;
        return model.getArtifactId();
    }
}
