<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">



<xsl:template match="master">

<xsl:for-each select="//intervenant">
	<xsl:document href="output/intervenants/{@idIntervenant}.html">
		  <html xmlns="http://www.w3.org/1999/xhtml">
		    <head>
		      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		      <title><xsl:value-of select="nom"/></title>
		    </head>
		    <body>
		      <a href="../../master.html">Index</a><br/>
		      <xsl:apply-templates select="."/>
		    </body>
		  </html>
	</xsl:document>
</xsl:for-each>

<xsl:for-each select="//enseignement">
	<xsl:document href="output/enseignements/{@idEnseignement}.html">
		  <html xmlns="http://www.w3.org/1999/xhtml">
		    <head>
		      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		      <title><xsl:value-of select="nom"/></title>
		    </head>
		    <body>
		      <a href="../../master.html">Index</a><br/>
		      <xsl:apply-templates select="."/>
		    </body>
		  </html>
	</xsl:document>
</xsl:for-each>

	<html>
		<head>
			<title>Master Luminy</title>
		</head>
		<body>
			<h1> Liste Intervenants </h1>
			<xsl:apply-templates select="intervenants"/>
			<h1> Liste Enseignements </h1>
			<xsl:apply-templates select="enseignements"/>
			
		</body>
	</html>

</xsl:template>


<xsl:template match="intervenants">
	<ul>
		<xsl:for-each select ="intervenant">
		<li><a href="output/intervenants/{@idIntervenant}.html"><xsl:value-of select="nom"/></a></li>
		</xsl:for-each>
	</ul>
</xsl:template>

<xsl:template match="enseignements">
	<ul>
		<xsl:for-each select ="enseignement">
		<li><a href="output/enseignements/{@idEnseignement}.html"><xsl:value-of select="nom"/></a></li>
		</xsl:for-each>
	</ul>
</xsl:template>


<xsl:template match="intervenant">
			<div id="{@idIntervenant}">
				<h2><xsl:value-of select="nom"/></h2>
				<ul>
					<li>Identifiant :<xsl:value-of select="identifiant"/></li>
					<li>Adresse Mail<xsl:value-of select="mail"/></li>
					<li>Téléphone :<xsl:value-of select="telephone"/></li>
					<li>Etablissement :<xsl:value-of select="etablissement"/></li><br/>
				
				</ul>
			</div>
</xsl:template>


<xsl:template match="enseignement">
			<div id="{@idEnseignement}">
				<h2><xsl:value-of select="nom"/></h2>
				<ul>
					<li>Identifiant :<xsl:value-of select="identifiant"/></li>
					<li>Nombre de crédits<xsl:value-of select="nombreCredit"/></li>
					<li>Résumé :<xsl:value-of select="resume"/></li>
					<xsl:if test="pre-requis"><li>Prérequis :<xsl:value-of select="pre-requis"/></li></xsl:if><br/>
				
				</ul>
			</div>
</xsl:template>
</xsl:stylesheet>
