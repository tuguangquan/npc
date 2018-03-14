// Source File Name:   PropertyUtil.java

package com.ctgu.npc.business.common.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil{

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    
    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     */
    public static Properties loadProperties(String resourcesPaths) {
        Properties props = new Properties();

        InputStream is = null;
        try {
            Resource resource = resourceLoader.getResource(resourcesPaths);
            is = resource.getInputStream();
            props.load(is);
        } catch (IOException ex) {
        } finally {
            IOUtils.closeQuietly(is);
        }
        return props;
    }
}
