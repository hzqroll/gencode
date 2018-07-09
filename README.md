# 介绍
  根据freemarker，自动生成代码
  只需要极少配置，即可生成spring基础代码的工具
  目录结构：
  -java：
    -dao
      -impl
    -service
      -impl
  -resources
    -model
    
  
  默认配置放在resources下面，文件名为 genConfig.properties
  数据库配置
  jdbc.url 
  jdbc.username
  jdbc.password
  jdbc.driver.class.name

  路径配置
  model路径
  modelDir=/src/main/resources/model
  java路径位置，默认就好
  javaPath=/src/main/java
  resourcesPath=/src/main/resources
  包名：需要自己重写
  packageName=com.a.b
