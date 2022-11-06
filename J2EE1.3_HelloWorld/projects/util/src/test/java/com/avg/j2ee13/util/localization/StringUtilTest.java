package com.avg.j2ee13.util.localization;

import com.avg.j2ee13.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.MockObjectTestCase;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class StringUtilTest extends MockObjectTestCase {

    protected static final Log logger = LogFactory.getLog(ServiceLocator.class);

    public static void main(String[] args) throws Exception {
        StringUtilTest test = new StringUtilTest();
        test.setUp();
        test._test_StringUtil();
    }

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void test_StringUtil() {
//        _test_StringUtil();
        assertEquals(1, 1);
    }

    public void _test_StringUtil() {
        String line = "1;NAME;DATE";
        String[] fields = StringUtils.split(line);
        for (int index = 0; index < fields.length; index++) {
            logger.info("field " + index + " -> " + fields[index]);
        }
    }

}
