<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/28
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="gbk"/>
    <meta http-equiv="Content-Language" content="zh-cn"/>
    <meta name="description" content="全球最大的中文web技术教程"/>
    <meta name="author" content="w3school.com.cn"/>
    <meta name="Copyright" content="Copyright w3school.com.cn all rights reserved"/>
    <title>W3SDemo</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/w3s.css"/>
</head>
<body id="homefirst">
    <div id="wrapper">
        <div id="header_index">
            <h1><a href="${basePath}/w3sindex" title="W3S在线教程" style="float:left;">W3s在线教程</a></h1>
            <div id="searchui">
                <form action="" method="get" id="searchform">
                    <input type="hidden" name="sitesearch" value="w3school.com.cn"/>
                    <input type="text" name="as_q" class="box" id="search_content" title="在此输入内容"/>
                    <input type="submit" value="GO" class="button" title="搜索"/>
                </form>
            </div>
        </div>
        <div id="navfirst">
            <ul id="menu">
                <li id="h"><a href="" title="HTML 系列教程">HTML 系列教程</a></li>
                <li id="b"><a href="" title="浏览器脚本教程">浏览器脚本</a></li>
                <li id="s"><a href="" title="服务器脚本教程">服务器脚本</a></li>
                <li id="d"><a href="" title="ASP.NET 教程">ASP.NET 教程</a></li>
                <li id="x"><a href="" title="XML 系列教程">XML 系列教程</a></li>
                <li id="ws"><a href="" title="Web Services 系列教程">Web Services 系列教程</a></li>
                <li id="w"><a href="" title="建站手册">建站手册</a></li>
            </ul>
        </div>
        <div id="navsecond">
            <h2>HTML教程</h2>
            <ul>
                <li><a href="" title="HTML教程">HTML</a></li>
                <li><a href="" title="HTML5教程">HTML5</a></li>
                <li><a href="" title="XHTML教程">XHTML</a></li>
                <li><a href="" title="CSS教程">CSS</a></li>
                <li><a href="" title="CSS3教程">CSS3</a></li>
            </ul>
            <h2>浏览器教程</h2>
            <ul>
                <li><a href="" title="JavaScript教程">JavaScript</a></li>
                <li><a href="" title="HTML DOM教程">HTMLDOM</a></li>
                <li><a href="" title="JQuery教程">JQuery</a></li>
            </ul>
            <h2>服务器脚本</h2>
            <ul>
                <li><a hr="" title="XML教程">XML</a></li>
                <li><a href="" title="DTD教程">DTD</a></li>
                <li><a href="" title="XML DOM教程">XML DOM教程</a></li>
                <li><a href="" title="XSL教程">XSL教程</a></li>
            </ul>
            <h2 id="link_about"><a href="" title="关于W3S">关于W3S</a></h2>
            <h2 id="link_help"><a href="" title="帮助W3School">帮助W3School</a></h2>
        </div>
        <div id="maincontent">
            <div class="idea" id="d1">
                <h2>领先的 Web 技术教程 - 全部免费</h2>
                <p>在 W3School，你可以找到你所需要的所有的网站建设教程。</p>
                <p>从基础的 HTML 到 CSS，乃至进阶的 XML、SQL、JS、PHP 和 ASP.NET。</p>
                <p><strong>从左侧的菜单选择你需要的教程！</strong></p>
            </div>
            <div class="idea" id="d2">
                <h3>完整的网站技术参考手册</h3>
                <p>我们的参考手册涵盖了网站技术的方方面面。</p>
                <p>其中包括W3C标准技术：HTML、CSS、XML 。以及其他技术，诸如 JavaScript、PHP、SQL 等。</p>
            </div>
            <div id="d8">
                <h3>W3School友情链接</h3>
                <p class="partner">
                    <a href="" target="_blank">FireFox中文社区</a>&nbsp;&nbsp;&nbsp;
                    <a href="" target="_blank">W3tech</a>&nbsp;&nbsp;&nbsp;
                </p>
            </div>
            <div id="d9">
                <h3>新浪微博</h3>
                <p><a href="" target="_blank">W3School官方微博</a></p>
            </div>
            <div id="d10">
                <h3>微信公众号</h3>
                <p>W3School 官方服务号</p>
            </div>
        </div>
        <div id="sidebar">
            <h2>参考手册</h2>
            <ul>
                <li><a href="/tags/index.asp" title="HTML 参考手册">HTML/HTML5 标签</a></li>
                <li><a href="/tags/html_ref_colornames.asp" title="HTML 颜色名">HTML 颜色</a></li>
                <li><a href="/cssref/index.asp" title="CSS 参考手册">CSS 1,2,3</a></li>
                <li><a href="/jsref/index.asp" title="JavaScript 参考手册">JavaScript</a></li>
                <li><a href="/jsref/index.asp" title="HTML DOM 参考手册">HTML DOM</a></li>
                <li><a href="/jquery/jquery_reference.asp" title="jQuery 参考手册">jQuery</a></li>
                <li><a href="/jquerymobile/jquerymobile_ref_data.asp" title="jQuery Mobile 参考手册">jQuery Mobile</a></li>
                <li><a href="/vbscript/vbscript_ref_functions.asp" title="VBScript 函数">VBScript</a></li>
                <li><a href="/asp/asp_ref.asp" title="ASP 参考手册">ASP</a></li>
                <li><a href="/ado/ado_reference.asp" title="ADO 参考手册">ADO</a></li>
                <li><a href="/aspnet/aspnet_reference.asp" title="ASP.NET 参考手册">ASP.NET</a></li>
                <li><a href="/php/php_ref.asp" title="PHP 参考手册">PHP 5.1</a></li>
                <li><a href="/xmldom/xmldom_reference.asp" title="XML DOM 参考手册">XML DOM</a></li>
                <li><a href="/xsl/xsl_w3celementref.asp" title="XSLT 元素参考手册">XSLT 1.0</a></li>
                <li><a href="/xpath/xpath_functions.asp" title="XPath、XQuery 以及 XSLT 函数">XPath 2.0</a></li>
                <li><a href="/xslfo/xslfo_reference.asp" title="XSL-FO 参考手册">XSL-FO</a></li>
                <li><a href="/wap/wml_reference.asp" title="WML 参考手册">WML 1.1</a></li>
                <li><a href="/glossary/index.asp" title="W3C 术语表和词典">W3C 术语表</a></li>
            </ul>
            <h2>字符集</h2>
            <ul>
                <li><a href="/tags/html_ref_charactersets.asp" title="HTML 字符集">HTML 字符集</a></li>
                <li><a href="/tags/html_ref_ascii.asp" title="HTML ASCII 参考手册">HTML ASCII</a></li>
                <li><a href="/tags/html_ref_entities.html" title="HTML ISO-8859-1 参考手册">HTML ISO-8859-1</a></li>
                <li><a href="/tags/html_ref_symbols.html" title="HTML 4.01 符号实体">HTML 符号</a></li>
            </ul>
        </div>
        <div id="footer">
            <p id="p1">
                W3School 简体中文版提供的内容仅用于培训和测试，不保证内容的正确性。通过使用本站内容随之而来的风险与本站无关。
            </p>
            <p id="p2">
                <a href="" target="_blank">使用条款</a>和<a href="" target="_blank">隐私条款</a>。版权所有，保留一切权利。
                赞助商：<a href="" title="上海有限公司" target="_blank">上海投资有限公司</a>
            </p>
        </div>
    </div>
</body>
</html>
