#Readme_ATM machine
 
##utilizare
se introduce de la tastatura suma ce doreste a fi impartita optim
in bancnote. Bancomatul functioneaza atat timp cat mai sunt bancnote in
automat astfel incat sa poata faca impartirea.
##concepte
Pentru implementarea acestui task am utilizat clasa Atm, pe care am facut-o de
tip Singleton si in care am implementat interfata  Subject.
(desgin patternul: observer)
Pentru a rezolva notificarea bancii(spre a se poate face refill cu bancnote),
atunci cand atm ul ramane fara un anumit tip de bancnote am utilizat Design
Patternul : Observer, astfel incat prin enumul Stock alert avem cele 4 tipuri
de alerte de refill ce pot aparea de-a lungul impartirii sumei introduse
de la tastatura. ATM-ul vetifica inainte de a atentiona bancile de tipul
de alerta(mesaj) pe care il trimite. Iar lista celor mai apropiate banci,
in cazul nostru am luat o singura banca interpreteaza mesajul (emailul de
trebuia trimis, si il afiseaza pe ecran)
In Main:
- introducerea de la tastatura a sumei dorite
- instantierea bancii si adaugarea in lista de banci apropiate
- trimiterea de mesaje
Bank :
  - interpretarea mesajelor de refill
ATM:
notes = tipul de bancnote ce exista in bancomat
noteCounter = vector de variabila intermediara in 
functie de care afisez cat ar trebui sa se
ia din fiecare tip de bancnota pentru a fi optim (bancnota cea mai mare prima)
note_freq = nr fiecarei tip de bancnota, pe care o updatez pe parcurs ce utilizez
            bancnotele
check_stock_notes() : functie pentru a putea atentiona banca cand stocul total scade sub 0
    
printCurrency(): ce va afisa ATM ul la ecran, impartirea sumei dorite in bancnote

countCurrency(): impart suma introdusa (ammount), si updatez nr fiecarei bancnote
                noteFrequency. In else if fac verificarea pentru utiliza fiecare bancnota,
                spre exemplu daca pe stoc mai e o singura bancnota de 100 lei, iar utilizatorul
                cere de la bancomat 234 lei, intai utilizez bancnota de 100 lei ramasa, apoi
                trec la cele de 50 de lei.



