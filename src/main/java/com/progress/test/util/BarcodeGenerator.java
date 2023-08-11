package com.progress.test.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.progress.test.entity.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class BarcodeGenerator {

    public void generateAndSetBarCode(Item item) {
        String barcodeData = generateBarcodeData(item);
        int barcodeWidth = 100;
        int barcodeHeight = 30;

        BufferedImage barcodeImage = createBarcodeImage(barcodeData, barcodeWidth, barcodeHeight);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(barcodeImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] barcodeByteArray = byteArrayOutputStream.toByteArray();
        item.setBarcode(barcodeByteArray);
    }
    private String generateBarcodeData(Item item) {

        String description = item.getDescription();
        int qty = item.getQty();
        double unitPrice = item.getUnitPrice();

        String barcodeData = description + "_" + qty + "_" + unitPrice;

        return barcodeData;
    }

    private BufferedImage createBarcodeImage(String barcodeData, int width, int height) {
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> itemMap = new Hashtable<>();
            itemMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            Code128Writer writer = new Code128Writer();
            BitMatrix bitMatrix = writer.encode(barcodeData, BarcodeFormat.CODE_128, width, height);

            int matrixWidth = bitMatrix.getWidth();
            int matrixHeight = bitMatrix.getHeight();
            BufferedImage bufferedImage = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < matrixWidth; x++) {
                for (int y = 0; y < matrixHeight; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0XFFFFFFFF);
                }
            }
            return bufferedImage;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
