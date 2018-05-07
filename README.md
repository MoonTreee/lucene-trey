# lucene
<p>基于lucene的小型中文搜索引擎</p>

![首页](https://github.com/MoonTreee/lucene-trey/blob/master/web/content/img/lucene_trey.png)

最近项目中需要自行进行搜索任务，比较紧急，只能采用字符匹配完成。看到全文搜索引擎的相关资料，尝试用之。
</p>
<p>使用方式：</p>

- 引入资源：通过文本文件或者数据库链接引入资源文件（需要相应修改IndexData的相关属性）
- 建立索引：通过 *IndexService* 的 *index()* 方法创建检索所需的索引
- 进行检索：制定相关的检索策略


<p>Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎（英文与德文两种西方语言）。Lucene的目的是为软件开发人员提供一个简单易用的工具包，以方便的在目标系统中实现全文检索的功能，或者是以此为基础建立起完整的全文检索引擎。Lucene是一套用于全文检索和搜寻的开源程式库，由Apache软件基金会支持和提供。Lucene提供了一个简单却强大的应用程式接口，能够做全文索引和搜寻。在Java开发环境里Lucene是一个成熟的免费开源工具。就其本身而言，Lucene是当前以及最近几年最受欢迎的免费Java信息检索程序库。人们经常提到信息检索程序库，虽然与搜索引擎有关，但不应该将信息检索程序库与搜索引擎相混淆。</p>

[百度百科 lucene](https://baike.baidu.com/item/Lucene/6753302) 

如下是Lucene引擎和数据库检索的对比（[来源是这里](https://www.chedong.com/tech/lucene.html)）：
<table width="100%" border="1" style="height: 283px">   <tbody>     <tr>       <td align="center" style="width: 9%; height: 16px">　</td>       <td align="center" style="width: 47%; height: 16px">Lucene全文索引引擎</td>       <td align="center" style="width: 40%; height: 16px">数据库</td>      </tr>     <tr>       <td style="width: 9%; height: 48px">索引</td>       <td style="width: 47%; height: 48px">将数据源中的数据都通过全文索引一一建立反向索引</td>       <td style="width: 40%; height: 48px">对于LIKE查询来说，数据传统的索引是根本用不上的。数据需要逐个便利记录进行GREP式的模糊匹配，比有索引的搜索速度要有多个数量级的下降。</td>     </tr>     <tr>        <td style="width: 9%; height: 49px">匹配效果</td>       <td style="width: 47%; height: 49px">通过词元(term)进行匹配，通过语言分析接口的实现，可以实现对中文等非英语的支持。</td>       <td style="width: 40%; height: 49px">使用：like "%net%" 会把netherlands也匹配出来，<br> 多个关键词的模糊匹配：使用like "%com%net%"：就不能匹配词序颠倒的xxx.net..xxx.com</td>     </tr>     <tr>       <td style="width: 9%; height: 32px">匹配度</td>        <td style="width: 47%; height: 32px">有匹配度算法，将匹配程度（相似度）比较高的结果排在前面。</td>       <td style="width: 40%; height: 32px">没有匹配程度的控制：比如有记录中net出现5词和出现1次的，结果是一样的。</td>     </tr>     <tr>       <td style="width: 9%; height: 32px">结果输出</td>       <td style="width: 47%; height: 32px">通过特别的算法，将最匹配度最高的头100条结果输出，结果集是缓冲式的小批量读取的。</td>       <td style="width: 40%; height: 32px">返回所有的结果集，在匹配条目非常多的时候（比如上万条）需要大量的内存存放这些临时结果集。</td>      </tr>     <tr>       <td style="width: 9%; height: 32px">可定制性</td>       <td style="width: 47%; height: 32px">通过不同的语言分析接口实现，可以方便的定制出符合应用需要的索引规则（包括对中文的支持）</td>       <td style="width: 40%; height: 32px">没有接口或接口复杂，无法定制</td>     </tr>     <tr>        <td style="width: 9%; height: 32px">结论</td>       <td style="width: 47%; height: 32px">高负载的模糊查询应用，需要负责的模糊查询的规则，索引的资料量比较大</td>       <td style="width: 40%; height: 32px">使用率低，模糊匹配规则简单或者需要模糊查询的资料量少</td>     </tr>   </tbody> </table>

# Update Info
- 2018.05.03  index界面，配置文件方式定位索引位置