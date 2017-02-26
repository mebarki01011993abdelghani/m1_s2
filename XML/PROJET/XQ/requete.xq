declare namespace saxon="http://saxon.sf.net/";
declare option saxon:output "method=xml";
declare option saxon:output "doctype-public=-//W3C//DTD XHTML 1.0 Strict//EN";
declare option saxon:output "doctype-system=http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
declare option saxon:output "omit-xml-declaration=no";
declare option saxon:output "indent=yes";

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> [7]
    <style type="text/css">
      body{{font-family: Verdana, Arial, Helvetica, sans-serif}}[8]
      table, th, td{{border: 1px solid gray; border-collapse:collapse}}
      .alt1{{background-color:mistyrose}}
      .alt2{{background-color:azure}}
      .th{{background-color:silver}}
    </style>
    <title>Liste des Enseignants</title>
  </head>
	<body>
	<h1>Liste des Enseignants</h1>
{
    for
        $nomIntervenants in doc("../XML/master.xml")//intervenant/nom
	order by $nomIntervenants
    return
        <ul>
            <li>{$nomIntervenants}
				{
				        for $idInter in fn:distinct-values($nomIntervenants/../identifiant)
				        order by $idInter
				        return 
				            <ul>
											Matieres enseignées :
								{
									for $matiere in doc("../XML/master.xml")//enseignement
									let $idRef := string($matiere/professeurs/ref-intervenant[@ref = $idInter]/../../nom)
									order by $idRef	
									return
										if (fn:compare($idInter,($matiere/professeurs/ref-intervenant[@ref = $idInter])) > 0) then
											<li>{$idRef}</li>
										else
											""
								}
				            </ul>
				 	}
						</li>
          </ul>
        
}
</body>
</html>
