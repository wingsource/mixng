<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"
                indent="yes"/>
    <xsl:template match="/">
        <xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
        <html lang="en" ng-app="mixng">
            <head>
                <xsl:for-each select="//metadata/meta">
                    <xsl:choose>
                        <xsl:when test="@name='title'">
                            <title><xsl:value-of select="@content"/></title>
                        </xsl:when>
                        <xsl:when test="@name='cache-control'">
                            <meta http-equiv="CACHE_CONTROL" content="{@content}"/>
                            <META HTTP-EQUIV="PRAGMA" CONTENT="{@content}"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <meta name="{@name}" content="{@content}"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>

                <link href="https://addfs-static.appspot.com/favicon.ico" rel="SHORTCUT ICON" />
                <xsl:for-each select="//link[@type='CSS']">
                    <link rel="stylesheet" href="{@url}"></link>
                </xsl:for-each>
                <style>
                    body {
                    padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
                    }
                </style>
            </head>
            <body>
                <div class="container">

                    <xsl:for-each select="//panel">
                        <!--
                            This code is added to make sure we render the page as Bootstrap expects in row-wise form.
                            Important thing to note here that we must have page definitions which will add all panel
                            widths to a multiple of 12. If it is not a multiple of 12 then the page look can be
                            broken.
                         -->
                        <xsl:if test="sum(./preceding-sibling::panel[*]/width) mod 12 = 0">
                            <xsl:if test="position() = 1">
                                <xsl:text disable-output-escaping="yes">&lt;div class="row-fluid"&gt;</xsl:text>
                            </xsl:if>
                            <xsl:if test="position() > 1">
                                <xsl:text disable-output-escaping="yes">&lt;/div&gt;</xsl:text>
                                <xsl:text disable-output-escaping="yes">&lt;div class="row-fluid"&gt;</xsl:text>
                            </xsl:if>

                        </xsl:if>
                        <xsl:for-each select="gadget">
                            <div class="col-md-{../width}">
                                <xsl:choose>
                                    <xsl:when test="content">
                                        <div id='{uuid}' spec='{url}'> <xsl:value-of select="content" disable-output-escaping="yes" ></xsl:value-of></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div id='{uuid}' class="gadgets-gadget-chrome" spec='{url}'> <xsl:value-of select="title"></xsl:value-of></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </div>
                        </xsl:for-each>

                        <xsl:if test="position() = count(//panel)">
                            <xsl:text disable-output-escaping="yes">&lt;/div&gt;</xsl:text>
                        </xsl:if>

                    </xsl:for-each>
                </div>
                <script type="text/javascript">
                    var modules = [<xsl:for-each select="//gadget">"<xsl:value-of select="id"></xsl:value-of>",</xsl:for-each>];

                </script>
            </body>
            <xsl:for-each select="//link[@type='js']">
                <script type="text/javascript" src="{@url}"></script>
            </xsl:for-each>

        </html>
    </xsl:template>
</xsl:stylesheet>