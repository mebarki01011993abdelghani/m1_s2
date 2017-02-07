<?xml version="1.0" encoding="utf-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

  <xsl:template name="liste-des-unites">
    <ul>  
      <xsl:for-each select="//unite">
        <li><a href="unites/{@id}.html"><xsl:value-of select="nom"/></a></li>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <xsl:template match="unite">
    <xsl:document href="unites/{@id}.html">
      <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
          <title>Master Informatique - <xsl:value-of select="nom"/></title>
        </head>
        <body>
          <xsl:variable name="code" select="translate(../../../nom, ' ', '_')"/>
          <a href="../index.html">Index</a> / <a href="../unites.html">Unites</a> <br/>
          <div id="{@id}">
            <h2><xsl:value-of select="nom"/></h2>
            <h4><a href="../parcours/{$code}.html" ><xsl:value-of select="../../../nom"/></a></h4>
            <xsl:if test="ref-intervenant">
              <ul>
                <xsl:for-each select="ref-intervenant">
                  <!-- <xsl:variable name="code" select="@ref"/> -->
                  <!-- <li><a href="{@ref}"><xsl:value-of select="//intervenant[@id=$code]/nom"/></a></li> -->
                  <li><a href="../intervenants/{@ref}.html"><xsl:value-of select="id(@ref)/nom"/></a></li>
                </xsl:for-each>
              </ul>
            </xsl:if>
            <p><xsl:value-of select="string(resume)" /></p>
          </div>
        </body>
      </html>
    </xsl:document>
  </xsl:template>

</xsl:stylesheet>