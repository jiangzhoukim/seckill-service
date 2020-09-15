package cn.jiang.configuration;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author mr.jiang
 * @version 1.0 Created in 2019/12/30 17:10
 */
public class LogIpConfig extends ClassicConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(cn.jiang.configuration.LogIpConfig.class);

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error("获取日志Ip异常", e);
        }
        return null;
    }
}
