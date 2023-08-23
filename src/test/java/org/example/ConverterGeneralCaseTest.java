package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConverterGeneralCaseTest extends ConverterTestBase {

    @Override
    public void testConvertSingleBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(3, "core", null));
        entityList.add(new TreeEntity(1, "left", 3));
        entityList.add(new TreeEntity(2, "right", 3));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    @Override
    public void testConvertSeveralBinaryTree() {
        Collection<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(10, "core1", null));
        entityList.add(new TreeEntity(1, "left", 10));
        entityList.add(new TreeEntity(20, "core2", null));
        entityList.add(new TreeEntity(3, "right", 10));
        entityList.add(new TreeEntity(4, "left", 20));
        entityList.add(new TreeEntity(5, "right", 20));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    @Override
    public void testConvertSeveralLevelBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(10, "core", null));
        entityList.add(new TreeEntity(1, "left1", 10));
        entityList.add(new TreeEntity(2, "right1", 10));
        entityList.add(new TreeEntity(3, "left2", 1));
        entityList.add(new TreeEntity(4, "left2", 2));
        entityList.add(new TreeEntity(5, "right2", 2));
        entityList.add(new TreeEntity(6, "right2", 1));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    @Override
    public void testConvertSingleNonBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(10, "core", null));
        entityList.add(new TreeEntity(1, "left", 10));
        entityList.add(new TreeEntity(2, "centre", 10));
        entityList.add(new TreeEntity(3, "right", 10));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    @Override
    public void testConvertSeveralNonBinaryTree() {
        Collection<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(10, "core1", null));
        entityList.add(new TreeEntity(1, "left", 10));
        entityList.add(new TreeEntity(20, "core2", null));
        entityList.add(new TreeEntity(3, "centre", 10));
        entityList.add(new TreeEntity(4, "left", 20));
        entityList.add(new TreeEntity(5, "centre", 20));
        entityList.add(new TreeEntity(6, "right", 10));
        entityList.add(new TreeEntity(7, "right", 20));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        checkTreeDTOFields(resultListOfTrees, entityList);
    }

    @Override
    public void testConvertSeveralLevelNoneBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(10, "core", null));
        entityList.add(new TreeEntity(1, "left1", 10));
        entityList.add(new TreeEntity(4, "left2", 1));
        entityList.add(new TreeEntity(5, "right2", 1));
        entityList.add(new TreeEntity(6, "centre2", 1));
        entityList.add(new TreeEntity(2, "right1", 10));
        entityList.add(new TreeEntity(7, "left2", 2));
        entityList.add(new TreeEntity(8, "right2", 2));
        entityList.add(new TreeEntity(9, "centre2", 2));
        entityList.add(new TreeEntity(3, "centre1", 10));
        entityList.add(new TreeEntity(11, "left2", 3));
        entityList.add(new TreeEntity(12, "right2", 3));
        entityList.add(new TreeEntity(13, "centre2", 3));

        List<TreeDTO> resultListOfTrees = Converter.convertAllTree(entityList);
        checkTreeDTOFields(resultListOfTrees, entityList);
    }
}
