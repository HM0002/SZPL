Elsõ sor: a pálya-mátrix ""szélessége<szóköz>magassága"", ez hibakezeéshez input ellenõrzéshez, és az utolsó elem a pályaelemek száma 
(legmagasabb sorszám+1, ha minden oké)

Utána a pálya mátrix: Üres sor: 4db szóköz. Mezõelválasztó: "," (késõbb ez alapján split string metódus!) 
Sínek megadása (ez lesz az ID is, plusz egy szóközzel az elején és végén): elsõ karakter nagybetûs S K V A, értelmeszerûen a típust definiálja, S:Sin V:Valto...
utána lévõ 3 karakter egy folytonos számláló, mely S000-rõl indul, ez a vonatok belépési pontja, így a pálya szélén kell kötelezõen lennie. 
(Példapályán pont nem teljesül, régi verzió)
Utána üres sor jelzi, hogy a pálya parsolás vége, utána lévõ blokk a vonatok
(Implementációs feltétel: ArrayList<PalyaElem> listába indexelve a sín sorszáma alapján, tehát a S015 kötelezõen a 015-ös helyre kerül)

Jármûvek felsorolása: ID majd szóköz következõ ID, ahol ID felépítése: Elsõ karakter a típus: M:Mozdony, K:Kocsi, C:Szeneskocsi. 
Következõ karakter: M és C típusnál kötelezõen 0 (üres szín), K típusnál a színt jelölõ szám, 1-9 között.
Következõ karakter: Hányadik vonat jármûve
Utolsó karakter sorfolyotonos index 0-tõl 9-ig.
Majd újsor jelzi a blokk végét.

Következõ blokk állomások felsorolása, majd szóköz, színkód száma, szóköz és TRUE vagy FALSE, ami azt adja meg, van-e
várakozó utas az állomáson.

Következõ blokk: szomszédosségi felsorolás, szintaktika:
<palyaelem sorszáma><szóköz><1. szomszéd sorszáma><szóköz><2. szomszéd> és opcionálisan 3. és negyedik szomszéd szóközzel elválasztva,
hibakezelési okokból folytonosan (plusz ellenõrzés típusnak megfelelõ számú szomszéd lett-e megadva!)
Kötelezõen beállítandó a S000-nek mindkét szomszédja S001! Ha nem ilyen, error!
Blokk végén üres sor.

Utolsó blokk a pálya adatai: egy szám, hány idõpillanat teljen el a vonatok indítása között.