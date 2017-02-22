declare option saxon:output "indent=yes";

<resultat>
{
    for
       	$enseignements in doc("../XML/master.xml")//,
        $y in (3,2,1)
    let $nom := $enseigments/nom
    return
        <item>
            {$nom}
        </item>
}
</resultat>
