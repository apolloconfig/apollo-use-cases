# Purpose

演示如何通过apollo管理将启动前需要加载的properties文件配置也管理起来, 常见于数据源或者一些不怎么变更的文件properties配置, 主要抽离文件配置的重复内容和屏蔽环境差异, 一般用于容器环境, 通过ENV注入所需变量配置.

# Instructions
- 约定应用加载的配置文件名称, 放到公共namespace抽离公共配置, 项目有需要特别修改的, 直接关联公共namespace覆盖或增加响应配置. 示例中使用`config.properties`和`db-config.properties`, 对应公共namespace `PLATFORM.config.properties`和`PLATFORM.db-config.properties`
  - config.properties: 不经常修改或者非数据源但启动前需要加载的配置项
  - db-config.properties: 数据源相关的配置
- 确保需要托管文件在项目中存在, 空文件也可.
- 选择apollo托管则全部以apollo上配置为准, 文件内已有内容会被覆盖.
- 除了公共properties文件外, 其他properties文件默认也会被扫到并从apollo拉取, 如果不存在会报错退出, 建立对应文件名的私有namespace即可, 如果不想404直接退出,可以传入环境变量 `APOLLO_FORCE_PROPERTIES=false`.
