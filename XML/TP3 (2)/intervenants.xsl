<?xml version="1.0" encoding="utf-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

  <xsl:template name="liste-des-intervenants">
    <ul>  
      <xsl:for-each select="//intervenant">
        <li><a href="intervenants/{@id}.html"><xsl:value-of select="nom"/></a></li>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <xsl:template match="intervenant">
    <xsl:document href="intervenants/{@id}.html">
      <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
          <title>Master Informatique - <xsl:value-of select="nom"/></title>
        </head>
        <body>
          <xsl:variable name="idInter" select="@id"/>
          <xsl:variable name="nameInter" select="nom"/>
          <a href="../index.html">Index</a> / <a href="../intervenants.html">Intervenants</a> <br/>
          <div id="{@id}">
            <h2><xsl:value-of select="nom"/></h2>
            <p><xsl:value-of select="email" /><br/><a href="http://{web}"><xsl:value-of select="web"/></a></p>
          </div>
          <div>
            <h4>Responsable Parcours</h4>
            <ul>
              <xsl:for-each select="//parcours">
                <xsl:if test="responsable=$nameInter">
                  <xsl:variable name="code" select="translate(nom, ' ', '_')"/>
                  <li><a href="../parcours/{$code}.html"><xsl:value-of select="nom"/></a></li>
                </xsl:if>
              </xsl:for-each>
            </ul>
          </div>
          <div>
            <h4>Unitées enseignées</h4>
            <ul>
              <xsl:for-each select="//unite">
                <xsl:if test="ref-intervenant/@ref=$idInter">
                  <li><a href="../unites/{@id}.html"><xsl:value-of select="nom"/></a></li>
                </xsl:if>
              </xsl:for-each>
            </ul>
          </div>
        </body>
      </html>
    </xsl:document>
  </xsl:template>

</xsl:stylesheet>