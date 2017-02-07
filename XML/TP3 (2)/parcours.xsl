<?xml version="1.0" encoding="utf-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

  <xsl:template name="liste-des-parcours">
    <ul>
      <xsl:for-each select="//parcours">
        <xsl:variable name="code" select="translate(nom, ' ', '_')"/>
        <li><a href="parcours/{$code}.html"><xsl:value-of select="nom"/></a></li>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <xsl:template match="parcours">
    <xsl:variable name="code" select="translate(nom, ' ', '_')"/>
    <xsl:document href="parcours/{$code}.html">
      <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
          <title>Master Informatique - <xsl:value-of select="nom"/></title>
        </head>
        <body>
          <a href="../index.html">Index</a> / <a href="../parcours.html">Parcours</a> <br/>
          <div id="{@id}">  
            <h2><xsl:value-of select="nom"/></h2>
            <p>
              Responsable : <a href="#"><xsl:value-of select="responsable"/></a><br/><br/>
              <xsl:value-of select="description"/><br/><br/>
              <xsl:value-of select="debouche"/><br/>
            </p>
            <xsl:for-each select="semestre">
              <h4>Semestre : </h4>
              <h5>Obligatoire</h5>
              <ul>
                <xsl:for-each select="obligatoires/unite">
                  <li><a href="../unites/{@id}.html"><xsl:value-of select="nom"/></a></li>
                </xsl:for-each>
              </ul>
              <h5>Option</h5>
              <ul>
                <xsl:for-each select="options/unite">
                  <li><a href="../unites/{@id}.html"><xsl:value-of select="nom"/></a></li>
                </xsl:for-each>
              </ul>
            </xsl:for-each>
          </div>
        </body>
      </html>
    </xsl:document>
  </xsl:template>

</xsl:stylesheet>