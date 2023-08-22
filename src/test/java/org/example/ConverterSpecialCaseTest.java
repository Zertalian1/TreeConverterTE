package org.example;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConverterSpecialCaseTest extends TestCase {

    public void testConvertSingleBinaryTree() {
        List<TreeEntity> entityList = new ArrayList<>();
        entityList.add(new TreeEntity(0, "core", null));
        entityList.add(new TreeEntity(1, "left", 0));
        entityList.add(new TreeEntity(2, "right", 0));

        List<TreeDTO> resultListOfTrees = Converter.convertTree(entityList);
        Assert.assertEquals(resultListOfTrees.size(),1);
        Assert.assertEquals(resultListOfTrees.get(0).getChildren().size(),2);

        TreeDTO leftLeaf = resultListOfTrees.get(0).getChildren().get(0);
        Assert.assertEquals(leftLeaf.getId().intValue(), 1);
        Assert.assertEquals(leftLeaf.getName(), "left");

        TreeDTO rightLeaf = resultListOfTrees.get(0).getChildren().get(1);
        Assert.assertEquals(rightLeaf.getId().intValue(), 2);
        Assert.assertEquals(rightLeaf.getName(), "right");
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

        for (TreeDTO tree: resultListOfTrees) {
            Assert.assertEquals(tree.getChildren().size(),2);

            TreeDTO leftLeaf = tree.getChildren().get(0);
            Assert.assertEquals(leftLeaf.getName(), "left");

            TreeDTO rightLeaf = tree.getChildren().get(1);
            Assert.assertEquals(rightLeaf.getName(), "right");

            if (tree.getId() == 0) {
                Assert.assertEquals(leftLeaf.getId().intValue(), 1);
                Assert.assertEquals(rightLeaf.getId().intValue(), 3);
            }
            if (tree.getId() == 2) {
                Assert.assertEquals(leftLeaf.getId().intValue(), 4);
                Assert.assertEquals(rightLeaf.getId().intValue(), 5);
            }
        }
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
        Assert.assertEquals(resultListOfTrees.get(0).getChildren().size(),2);

        TreeDTO leftBranch = resultListOfTrees.get(0).getChildren().get(0);
        Assert.assertEquals(leftBranch.getId().intValue(), 1);
        Assert.assertEquals(leftBranch.getName(), "left1");
        Assert.assertEquals(leftBranch.getChildren().size(), 2);
        TreeDTO leftLeaf = leftBranch.getChildren().get(0);
        TreeDTO rightLeaf = leftBranch.getChildren().get(1);
        Assert.assertEquals(leftLeaf.getId().intValue(), 3);
        Assert.assertEquals(leftLeaf.getName(), "left2");
        Assert.assertEquals(rightLeaf.getId().intValue(), 6);
        Assert.assertEquals(rightLeaf.getName(), "right2");


        TreeDTO rightBranch = resultListOfTrees.get(0).getChildren().get(1);
        Assert.assertEquals(rightBranch.getId().intValue(), 2);
        Assert.assertEquals(rightBranch.getName(), "right1");
        Assert.assertEquals(rightBranch.getChildren().size(), 2);
        leftLeaf = rightBranch.getChildren().get(0);
        rightLeaf = rightBranch.getChildren().get(1);
        Assert.assertEquals(leftLeaf.getId().intValue(), 4);
        Assert.assertEquals(leftLeaf.getName(), "left2");
        Assert.assertEquals(rightLeaf.getId().intValue(), 5);
        Assert.assertEquals(rightLeaf.getName(), "right2");
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

        TreeDTO leftLeaf = resultListOfTrees.get(0).getChildren().get(0);
        Assert.assertEquals(leftLeaf.getId().intValue(), 1);
        Assert.assertEquals(leftLeaf.getName(), "left");

        TreeDTO centreLeaf = resultListOfTrees.get(0).getChildren().get(1);
        Assert.assertEquals(centreLeaf.getId().intValue(), 2);
        Assert.assertEquals(centreLeaf.getName(), "centre");

        TreeDTO rightLeaf = resultListOfTrees.get(0).getChildren().get(2);
        Assert.assertEquals(rightLeaf.getId().intValue(), 3);
        Assert.assertEquals(rightLeaf.getName(), "right");
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

        for (TreeDTO tree: resultListOfTrees) {
            Assert.assertEquals(tree.getChildren().size(),3);

            TreeDTO leftLeaf = tree.getChildren().get(0);
            Assert.assertEquals(leftLeaf.getName(), "left");

            TreeDTO centreLeaf = tree.getChildren().get(1);
            Assert.assertEquals(centreLeaf.getName(), "centre");

            TreeDTO rightLeaf = tree.getChildren().get(2);
            Assert.assertEquals(rightLeaf.getName(), "right");

            if (tree.getId() == 0) {
                Assert.assertEquals(leftLeaf.getId().intValue(), 1);
                Assert.assertEquals(centreLeaf.getId().intValue(), 3);
                Assert.assertEquals(rightLeaf.getId().intValue(), 6);
            }
            if (tree.getId() == 2) {
                Assert.assertEquals(leftLeaf.getId().intValue(), 4);
                Assert.assertEquals(centreLeaf.getId().intValue(), 5);
                Assert.assertEquals(rightLeaf.getId().intValue(), 7);
            }
        }
    }


}