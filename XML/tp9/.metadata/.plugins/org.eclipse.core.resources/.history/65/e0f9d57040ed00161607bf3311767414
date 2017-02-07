<?xml version="1.0" encoding="utf-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

  <xsl:include href="unites.xsl"/>
  <xsl:include href="intervenants.xsl"/>
  <xsl:include href="parcours.xsl"/>
  
  <xsl:output
    doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
    method="html"/>

  <xsl:template match="/">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
        <title>Master Informatique</title>
      </head>
      <body>
        <a href="unites.html">Unites</a><br/>
        <a href="intervenants.html">Intervenants</a><br/>
        <a href="parcours.html">Parcours</a>
        <xsl:document href="unites.html">
          <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
              <title>Master Informatique - Unites</title>
            </head>
            <body>
              <a href="index.html">Index</a><br/>
              <xsl:call-template name="liste-des-unites" />
            </body>
          </html>
        </xsl:document>
        <xsl:document href="intervenants.html">
          <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
              <title>Master Informatique - Intervenants</title>
            </head>
            <body>
              <a href="index.html">Index</a> <br/>
              <xsl:call-template name="liste-des-intervenants" />
            </body>
          </html>
        </xsl:document>
        <xsl:document href="parcours.html">
          <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
              <title>Master Informatique - Parcours</title>
            </head>
            <body>
              <a href="index.html">Index</a> <br/>
              <xsl:call-template name="liste-des-parcours" />
            </body>
          </html>
        </xsl:document>

        <xsl:apply-templates select="//unite"/>
        <xsl:apply-templates select="//intervenant"/>
        <xsl:apply-templates select="//parcours"/>

      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>