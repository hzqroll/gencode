# 介绍
  根据freemarker，自动生成代码<br>
  只需要极少配置，即可生成spring基础代码的工具<br>
  目录结构：<br>
  -java：<br>
    -dao<br>
      -impl<br>
    -service<br>
      -impl<br>
  -resources<br>
    -model<br>
    
#配置文件
  默认配置放在resources下面，文件名为 genConfig.properties<br>
  数据库配置<br>
  jdbc.url <br>
  jdbc.username<br>
  jdbc.password<br>
  jdbc.driver.class.name<br>

  路径配置<br>
  model路径<br>
  modelDir=/src/main/resources/model<br>
  java路径位置，默认就好<br>
  javaPath=/src/main/java<br>
  resourcesPath=/src/main/resources<br>
  包名：需要自己重写<br>
  packageName=com.a.b<br>
