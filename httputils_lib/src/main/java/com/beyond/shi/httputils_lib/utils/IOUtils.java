
package com.beyond.shi.httputils_lib.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO操作
 *
 * @author venshine
 */
public class IOUtils {

    /**
     * Close closeable object
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {

            }
        }
    }

}
