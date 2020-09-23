package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.mapper.bo.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CodeTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CodeTest.class);
  List<Class<?>> list = new ArrayList<>();

  @BeforeEach
  public  void init() {
    list.add(User.class);
  }

  /**
   * @description: 生成原子层 IGridAsmtParamsAutoSV
   * @author: youpd@asiainfo.com
   * @date: 2020-04-14 15:14
   */
  @Test
  public void AutoSV() {

    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\service\\atom\\interfaces\\";
        String fileName = "IGridAsmtParamsAutoSV.java";
        File file = new File(filePath + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  @Test
  public void AutoSVImpl() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\service\\atom\\impl\\";
        String fileName = "GridAsmtParamsAutoSVImpl.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * @description: 复制VO
   * @author: youpd@asiainfo.com
   * @date: 2020-04-14 16:50
   */
  @Test
  public void VO() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      String path = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\dao\\mapper\\bo\\";
      String newPath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-api\\src\\main\\java\\com\\ai\\channel\\asmt\\api\\base\\vo\\";
      File oldFile = new File(path + SimpleName + ".java");
      if (!oldFile.exists()) {
        continue;
      }
      File newFile = new File(newPath + SimpleName + "VO.java");
      if (newFile.exists()) {
        newFile.delete();
      }
      try {
        FileUtils.copyFile(oldFile, newFile);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }


  /**
   * @description: QueryGridAsmtParamsRequest
   * @author: youpd@asiainfo.com
   * @date: 2020-04-14 17:04
   */
  @Test
  public void request() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-api\\src\\main\\java\\com\\ai\\channel\\asmt\\api\\base\\param\\";
        String fileName = "QueryGridAsmtParamsRequest.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }


  @Test
  public void BusiSV() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\service\\business\\interfaces\\";
        String fileName = "IGridAsmtParamsBusiSV.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }


  @Test
  public void BusiSVImpl() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\service\\business\\impl\\";
        String fileName = "GridAsmtParamsBusiSVImpl.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }


  @Test
  public void SV() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-api\\src\\main\\java\\com\\ai\\channel\\asmt\\api\\base\\interfaces\\";
        String fileName = "IGridAsmtParamsSV.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * @description:Impl
   * @author: youpd@asiainfo.com
   * @date: 2020-04-14 17:14
   */

  @Test
  public void Impl() {
    for (Class cls : list) {
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "GridAsmtParams";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\api\\base\\impl\\";
        String fileName = "GridAsmtParamsSVImpl.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String newFileName = filePath + fileName.replaceAll(replace, SimpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(replace, SimpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }


  @Test
  public void build_criteria() {
    for (Class cls : list) {
      //生成代码
      StringBuilder stringBuilder = new StringBuilder();
      Field[] declaredFields = cls.getDeclaredFields();
      for (Field field : declaredFields) {
        if (field.getGenericType().toString().equals("class java.util.Date")) {
          continue;
        } else {
          String name = field.getName();
          name = name.substring(0, 1).toUpperCase() + name.substring(1);
          String str = "if (!ObjectUtils.isEmpty(obj.get" + name + "())) { criteria.and" + name + "EqualTo(obj.get" + name + "());}";
          stringBuilder.append(str);
        }
      }
      String SimpleName = cls.getSimpleName();
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String replace = "//build_criteria";
        String filePath = "C:\\1work\\idea\\chan-srv-asmt\\chan-srv-asmt-core\\src\\main\\java\\com\\ai\\channel\\asmt\\service\\atom\\impl\\";
        String fileName = SimpleName + "AutoSVImpl.java";
        File file = new File(filePath + fileName);
        if (!file.exists()) {
          continue;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        File newFile = new File(filePath + fileName + "_temp");
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "utf-8"));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          writer.write(tempString);
          writer.newLine();
          if (tempString.contains(replace)) {//替换内容
            writer.write(stringBuilder.toString());
            writer.newLine();

          }
        }
        reader.close();
        writer.flush();
        writer.close();
        if (file.exists()) {
          boolean delete = file.delete();
          System.out.println(delete);
        }
        newFile.renameTo(new File(filePath + fileName));
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  @Test
  public void build_criteria_new() {
    for (Class cls : list) {
      //生成代码
      StringBuilder stringBuilder = new StringBuilder();
      Field[] declaredFields = cls.getDeclaredFields();
      for (Field field : declaredFields) {
        if (field.getGenericType().toString().equals("class java.util.Date")) {
          continue;
        } else {
          String name = field.getName();
          name = name.substring(0, 1).toUpperCase() + name.substring(1);
          String str = "if (!ObjectUtils.isEmpty(obj.get" + name + "())) { criteria.and" + name + "EqualTo(obj.get" + name + "());}";
          stringBuilder.append(str);
        }
      }
      System.out.println(stringBuilder.toString());
    }
  }

  public static void main(String[] args) {
    User a= JSONObject.parseObject("",User.class);
    System.out.println(a);
    /*//生成代码
    Class<GsopGridSysParam> cls=GsopGridSysParam.class;
    StringBuilder stringBuilder = new StringBuilder();
    Field[] declaredFields = cls.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.getGenericType().toString().equals("class java.util.Date")) {
        continue;
      } else {
        String name = field.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        String str = "if (!ObjectUtils.isEmpty(obj.get" + name + "())) { criteria.and" + name + "EqualTo(obj.get" + name + "());}";
        stringBuilder.append(str);
      }
    }
    System.out.println(stringBuilder.toString());
*/
  }
}
