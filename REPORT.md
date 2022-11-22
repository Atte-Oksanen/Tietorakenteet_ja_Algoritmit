# Kurssitehtävien oppima ja raportit

Kirjoita jokaisesta alla olevasta kurssitehtävästä parilla lauseella miten tehtävän tekeminen sujui ja mitä siitä opit.

Jos tehtävässä pyydetään **raportoimaan** jotain, kirjoita myös nämä raportit tähän dokumenttiin.

## 00-init

Tehtävän teko takkuili aluksi, sillä en osannut vielä lukea testien antamia virheitä, mutta ohjevideon katsottuani ymmärsin ongelman juuren ja sain tehtävän nopeasti tehtyä.

## 01-arrays

Tehtävä oli mukava palautus algoritmisempaan ohjelmointiin, sillä aiemmilla kursseilla on enemmänkin keskitytty olio-ohjelmoinnin perusteisiin ja sen erilaisiin käyttötarkoituksiin.

## 02-mode

![Graph](02-mode_graph.png)

Kuten yllä olevasta kuvaajasta voi nähdä, toteutukseni suoritusaika kasvaa eksponentiaalisesti suhteessa aineiston kokoon, eli aikakompleksisuus on O(n^2). Tämä johtuu algoritmin alussa suoritettavasta `sort`-metodista, jossa aineisto järjestetään bubble-sort tyyppisellä algoritmilla. Parantamalla `sort`-metodin algoritmia, saadaan myös `findMode`-metodi suoriutumaan nopeammin. `Sort`-metodia lukuunottamatta `findMode`-metodin algoritmin aikakompleksisuus on O(n).

Tehtävä itsessään oli mielekäs ja sopivasti haastava. Tehtävää tehdessäni pikaisesti ymmärsin, että pullonkaulana toimi `sort`-metodi, ja kurssin edetessä tiedän joutuvani parantamaan sen tehokkuutta huomattavasti.

## 03-draw

Tämä tehtävä oli ehdottomasti vaikein, mitä olen tähän mennessä tehnyt. Vaikeutta loi `Predicate`-muuttujan käyttö ja sen jälkeen itse algoritmin rakentaminen.


## 04-1-stack

Tämä tehtävä oli helpommin ymmärrettävä kuin aiempi, mutta huomattavasti työläämpi. Asiaan saattaa vaikuttaa pinon tunteminen aiemmin jo käsitteellisesti, jolloin sen toteuttaminen oli helpompaa.


## 04-2-queue

Oletin tehtävän helpoksi edellisen tehtävän mutkattomuuden vuoksi, mutta tämän takia lähdin ratkaisemaan tehtävää aivan väärällä tavalla ensiksi. Kun ymmärsin toteutukseni toimimattomuuden, aloitin kokonaan alusta ja sain tehtävän tehtyä yllättävän nopeasti. Ehdottomasti tärkein asia, jonka opin, on taulukoiden muistitehokas manipulointi.

## 04-3-linkedlist

Edellisestä tehtävästä nöyrryttäni tämän algoritmin luominen olikin huomattavasti helpompaa. Luentomateriaaleista oli tässä tehtävässä erityisen paljon apua toteutuksen ymmärtämiseksi. Katsellessani javan omien kirjastojen tietorakenneratkaisuja olin yllättynyt, että omani oli erittäin samanlainen kuin siellä (Tietenkin javan LinkedList on linkattu molempiin suuntiin). Testailin myös suoritusaikoja omalla ja javan toteutuksilla ja mielestäni ainoastaan kahteen suuntaan linkkaaminen tekee javan omasta toteutuksesta nopeamman, ja ArrayListin tekee nopeammaksi sen taulukkorakenne, jolloin varsinkin indekseihin perustuvien metodien ajaminen on huomattavasti tehokkaampaa. 

## 05-binsearch

Käsitteellisesti puolitushaku oli mielestäni helpompi kuin aiemmin toteutetut tietorakenteet, eikä sen toteuttamisessa tullut käytännössä mitään yllätyksiä esiin. Ajatus puolitushausta oli saanut muhia pidemmän aikaa pääkopassa, joka saattoi myös auttaa toteutuksen teossa.

## 05-invoices

Vaikka tehtävä vaikutti aluksi haastavalta, osoittautuikin se huomattavasti helpommaksi kuin alunperin luulin. Valitsin lajittelualgoritmiksi rekursiivisen heap-sortin, koska se vaikutti parhaalta tehtävän tarkoitukseen. En tehnyt algoritmia stabiiliksi, koska tehtävä ei sitä vaatinut, mutta se pitää pitää mielessä, jos myöhemmin käytettynä siitä tulee ongelmia.

## 67-phonebook

Tähän tehtävään liittyy raportti! Lue ohjeet!

## Valinnaiset tehtävät

Jos teit jotain valinnaisia tehtäviä, mainitse se täällä että ne tulevat varmasti arvioiduksi.

# Yleistä koko kurssista ja kurssin tehtävistä

Yleistä palautetta ja kehitysehdotuksia, kiitos!