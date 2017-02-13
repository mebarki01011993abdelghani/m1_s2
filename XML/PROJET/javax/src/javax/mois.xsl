<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : mois.xsl
    Created on : February 13, 2017, 10:15 PM
    Author     : scra
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>mois.xsl</title>
            </head>
            <body>
                    <ul>
                        <xsl:for-each select="//mois">
                            <li>
                                <a>
                                    <xsl:value-of select="nom"/>
                                </a>
                            </li>
                        </xsl:for-each>
                    </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
