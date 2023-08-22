package org.example;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.*;

public class ConverterSpecialCaseTest extends TestCase implements TreeResultAnalyzer{

    public void testConvertSingleBinaryTree() {
        Collection<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left", 0));
        entityList.add(new TreeEntity(2, "right", 0));

        List<TreeDTO> resultListOfTrees = Converter.convertTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),1);
        Assert.assertEquals(resultListOfTrees.get(0).getChildren().size(),2);

        checkTreeDTOFields(resultListOfTrees, entityList);

    }

    public void testConvertSeveralBinaryTree() {
        Collection<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left", 0));
        entityList.add(new TreeEntity(2, "core", null));
        entityList.add(new TreeEntity(3, "right", 0));
        entityList.add(new TreeEntity(4, "left", 2));
        entityList.add(new TreeEntity(5, "right", 2));

        List<TreeDTO> resultListOfTrees = Converter.convertTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),2);
        Assert.assertEquals(resultListOfTrees.get(0).getChildren().size(),2);
        Assert.assertEquals(resultListOfTrees.get(1).getChildren().size(),2);

        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    public void testConvertSeveralLevelBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left1", 0));
        entityList.add(new TreeEntity(2, "right1", 0));
        entityList.add(new TreeEntity(3, "left2", 1));
        entityList.add(new TreeEntity(4, "left2", 2));
        entityList.add(new TreeEntity(5, "right2", 2));
        entityList.add(new TreeEntity(6, "right2", 1));

        List<TreeDTO> resultListOfTrees = Converter.convertTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),1);

        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    public void testConvertSingleNonBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left", 0));
        entityList.add(new TreeEntity(2, "centre", 0));
        entityList.add(new TreeEntity(3, "right", 0));

        List<TreeDTO> resultListOfTrees = Converter.convertTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),1);
        Assert.assertEquals(resultListOfTrees.get(0).getChildren().size(),3);

        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    public void testConvertSeveralNonBinaryTree() {
        Collection<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left", 0));
        entityList.add(new TreeEntity(2, "core", null));
        entityList.add(new TreeEntity(3, "centre", 0));
        entityList.add(new TreeEntity(4, "left", 2));
        entityList.add(new TreeEntity(5, "centre", 2));
        entityList.add(new TreeEntity(6, "right", 0));
        entityList.add(new TreeEntity(7, "right", 2));

        List<TreeDTO> resultListOfTrees = Converter.convertTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),2);

        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    public void testConvertSeveralLevelNoneBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left1", 0));
        entityList.add(new TreeEntity(4, "left2", 1));
        entityList.add(new TreeEntity(5, "right2", 1));
        entityList.add(new TreeEntity(6, "centre2", 1));
        entityList.add(new TreeEntity(2, "right1", 0));
        entityList.add(new TreeEntity(7, "left2", 2));
        entityList.add(new TreeEntity(8, "right2", 2));
        entityList.add(new TreeEntity(9, "centre2", 2));
        entityList.add(new TreeEntity(3, "centre1", 0));
        entityList.add(new TreeEntity(10, "left2", 3));
        entityList.add(new TreeEntity(11, "right2", 3));
        entityList.add(new TreeEntity(12, "centre2", 3));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),1);
        Assert.assertEquals(resultListOfTrees.get(0).getChildren().size(),3);

        checkTreeDTOFields(resultListOfTrees, entityList);
    }
}