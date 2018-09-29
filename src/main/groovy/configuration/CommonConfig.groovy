package configuration

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by Kateryna on 04.11.2017.
 */
class CommonConfig {
    static user = System.getProperty('user')
    static password = System.getProperty('password')
    static context = "/bamboo"
    static String projKey
    static String planKey
    static String projName
    static String planName
    static String bambooHome = "${System.env.BAMBOO_HOME}"

    static Path getBuildDir(){
        return Paths.get(bambooHome, "xml-data", "build-dir", "${projKey}-${planKey}-JOB1")
    }
}