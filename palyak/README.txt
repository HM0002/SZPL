Els� sor: a p�lya-m�trix ""sz�less�ge<sz�k�z>magass�ga"", ez hibakeze�shez input ellen�rz�shez, �s az utols� elem a p�lyaelemek sz�ma 
(legmagasabb sorsz�m+1, ha minden ok�)

Ut�na a p�lya m�trix: �res sor: 4db sz�k�z. Mez�elv�laszt�: "," (k�s�bb ez alapj�n split string met�dus!) 
S�nek megad�sa (ez lesz az ID is, plusz egy sz�k�zzel az elej�n �s v�g�n): els� karakter nagybet�s S K V A, �rtelmeszer�en a t�pust defini�lja, S:Sin V:Valto...
ut�na l�v� 3 karakter egy folytonos sz�ml�l�, mely S000-r�l indul, ez a vonatok bel�p�si pontja, �gy a p�lya sz�l�n kell k�telez�en lennie. 
(P�ldap�ly�n pont nem teljes�l, r�gi verzi�)
Ut�na �res sor jelzi, hogy a p�lya parsol�s v�ge, ut�na l�v� blokk a vonatok
(Implement�ci�s felt�tel: ArrayList<PalyaElem> list�ba indexelve a s�n sorsz�ma alapj�n, teh�t a S015 k�telez�en a 015-�s helyre ker�l)

J�rm�vek felsorol�sa: ID majd sz�k�z k�vetkez� ID, ahol ID fel�p�t�se: Els� karakter a t�pus: M:Mozdony, K:Kocsi, C:Szeneskocsi. 
K�vetkez� karakter: M �s C t�pusn�l k�telez�en 0 (�res sz�n), K t�pusn�l a sz�nt jel�l� sz�m, 1-9 k�z�tt.
K�vetkez� karakter: H�nyadik vonat j�rm�ve
Utols� karakter sorfolyotonos index 0-t�l 9-ig.
Majd �jsor jelzi a blokk v�g�t.

K�vetkez� blokk �llom�sok felsorol�sa, majd sz�k�z, sz�nk�d sz�ma, sz�k�z �s TRUE vagy FALSE, ami azt adja meg, van-e
v�rakoz� utas az �llom�son.

K�vetkez� blokk: szomsz�doss�gi felsorol�s, szintaktika:
<palyaelem sorsz�ma><sz�k�z><1. szomsz�d sorsz�ma><sz�k�z><2. szomsz�d> �s opcion�lisan 3. �s negyedik szomsz�d sz�k�zzel elv�lasztva,
hibakezel�si okokb�l folytonosan (plusz ellen�rz�s t�pusnak megfelel� sz�m� szomsz�d lett-e megadva!)
K�telez�en be�ll�tand� a S000-nek mindk�t szomsz�dja S001! Ha nem ilyen, error!
Blokk v�g�n �res sor.

Utols� blokk a p�lya adatai: egy sz�m, h�ny id�pillanat teljen el a vonatok ind�t�sa k�z�tt.