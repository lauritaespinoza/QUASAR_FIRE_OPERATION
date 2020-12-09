package com.quasar.service.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MessageUtilsTest {

    @Test
    public void ShouldReturnNullWhenTransformToArray2DReceiveInvalidParams() {
        String[][] result1 = MesaggeUtils.transformToArray2D(null, 2);
        String[][] result2 = MesaggeUtils.transformToArray2D(new ArrayList<>(), -5);
        assertNull(result1);
        assertNull(result2);
    }

    @Test
    public void ShouldReturnTransformArray2DWhenReceiveValidEncodeMessageList() {
        List<List<String>> encodeList = createEncodeMessageListMock();
        String[][] encodeArray = createEncodeArrayMock();
        String[][] result = MesaggeUtils.transformToArray2D(encodeList, 2);
        assertEquals(7, result[0].length);
        assertEquals(3, result.length);
        assertArrayEquals( encodeArray, result);
    }

    @Test
    public void ShouldReturnNullWhenReverseArrayMethodReceiveInvalidParams(){
        String[][] result = MesaggeUtils.reverseArray(null);
        assertNull(result);
    }

    @Test
    public void ShouldReturnAnArrayReversedWhenReceiveValidParams(){
        String[][] encodeArray = createEncodeArrayMock();
        String[][] expect = createEncodeArrayReversedMock();
        String[][] result = MesaggeUtils.reverseArray(encodeArray);
        assertNotNull(result);
        assertArrayEquals(expect, result);
    }

    @Test
    public void ShouldReturnSingleWordWhenReceiveAListWithTheSameWords(){
        String expect = "mensaje";
        List<String> params = Arrays.asList("mensaje", "mensaje", "mensaje");
        String result = MesaggeUtils.selectWord(params);
        assertEquals(expect, result);
    }

    @Test
    public void ShouldReturnPrincipalWordsWhenReceiveAListWithSomeWordsDifferent(){
        String expect = "es mensaje secreto";
        List<String> params = Arrays.asList("es", "mensaje", "es", "mensaje", "secreto");
        String result = MesaggeUtils.selectWord(params);
        assertEquals(expect, result);
    }

    private List<List<String>> createEncodeMessageListMock(){
        List<List<String>> encode = new ArrayList<>();
        encode.add( Arrays.asList("este","","","mensaje","", "muy", "largo"));
        encode.add( Arrays.asList("","es","","","secreto"));
        encode.add( Arrays.asList("este","","un","",""));
        return encode;
    }

    private String[][] createEncodeArrayMock(){
        String[][] encode = new String[3][];
        encode[0] = new String[]{"este", "", "", "mensaje", "", "muy", "largo"};
        encode[1] = new String[]{"", "es", "", "", "secreto"};
        encode[2] = new String[]{"este", "", "un", "", ""};
        return encode;
    }

    private String[][] createEncodeArrayReversedMock(){
        String[][] encode = new String[3][];
        encode[0] = new String[]{"largo", "muy", "", "mensaje", "", "", "este"};
        encode[1] = new String[]{"secreto", "", "", "es", ""};
        encode[2] = new String[]{"", "", "un", "", "este"};
        return encode;
    }
}