package org.nr.tour.common.util;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface QRCodeService {

    String TYPE_PNG = "PNG";
    String TYPE_JPG = "JPEG";
    String TYPE_GIF = "GIF";

    boolean generate(OutputStream outputStream, String content, int size, String type) throws IOException, WriterException;

}
