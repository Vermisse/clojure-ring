# 环境搭建

1. 安装Eclipse Neon以上版本(低版本亲测不可用)，并安装逆时针插件，可直接在Eclipse Marketplace中搜索`counterclockwise`或`clojure`
2. 安装MySQL，并执行db.sql初始化数据库(其实只有一个表)
3. 打包项目，使用`lein uberjar`命令
4. 在命令行中使用`java -jar web-1.0.0-standalone.jar`启动项目(压缩包太大没有上传，请自行编译)
