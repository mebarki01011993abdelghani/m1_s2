declare option saxon:output "indent=yes";

<resultat>
{
    for
       	$intervenant in doc("../XML/master.xml")//intervenants
    let $nom := $intervenant//nom
    return
        <item>
            {$nom}
        </item>
}
</resultat>
