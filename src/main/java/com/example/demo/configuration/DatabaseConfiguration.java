package com.example.demo.configuration;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DatabaseConfiguration
{
    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Bean
    public boolean instantiate()
    {
        /*Date datum1 = new Date();
        Artikal artikal1 = new Artikal("1L","Belo",50,"Mnogo dobro",200);
        artikalRepository.save(artikal1);
        Artikal artikal2 = new Artikal("2L","Mesano",100,"Opa",100);
        artikalRepository.save(artikal2);

        Admin admin1 = new Admin("Admin1","123");
        adminRepository.save(admin1);
        Dostavljac dostavjac1 = new Dostavljac("Dostavljac1","123","Aleksandar","Ristic","M",datum1,Uloga.DOSTOVLJAC);
        dostavljacRepository.save(dostavjac1);
        Dostavljac dostavjac2 = new Dostavljac("Dostavljac2","123","Nikola","Kojic","M",datum1,Uloga.DOSTOVLJAC);
        Komentar komnetar1 = new Komentar(1L,"Sto je lepo JOOJ",5);
        Kupac kupac1 = new Kupac("Kupac1","123","Lola","Pasic","Z",datum1,Uloga.KUPAC,2);
        kupacRepository.save(kupac1);
        Kupac kupac2 = new Kupac("Kupac2","123","Marija","Kopcic","Z",datum1,Uloga.KUPAC,4);
        Menadzer menadzer1 = new Menadzer("Menazder1","123","Imenko","Prezimenic","Z",datum1,Uloga.MENADZER,restoranRepository.getByNaziv("Kod Riste"));
        menadzerRepository.save(menadzer1);
        Menadzer menadzer2 = new Menadzer("Menazder2","123","Bobi","Bobic","Z",datum1,Uloga.MENADZER);
        Lokacija lokacija1 = new Lokacija(1L,"BoguIzaNogu",23,14);

        Porudzbina porudzbina1 = new Porudzbina(1L,datum1,23, Status.OBRADA);
        Porudzbina porudzbina2 = new Porudzbina(2L,datum1,50, Status.CEKA_DOSTAVLJACA);
        Restoran restoran1 = new Restoran(1L,"KodRiste","Narodni");
        restoranRepository.save(restoran1);

        TipKupac zlatni = new TipKupac(1L,VrstaPotrosaca.ZLATNI,20,10);

        artikal1.setPorudzbina(porudzbina1);

        artikal2.setPorudzbina(porudzbina2);

        artikal1.setRestoran(restoran1);
        artikal2.setRestoran(restoran1);
        komnetar1.setKupac(kupac1);
        komnetar1.setRestoran(restoran1);
        kupac1.setTipKupca(zlatni);




        Set<Artikal> artikli = new HashSet<>();
        artikli.add(artikal1);
        artikli.add(artikal2);

        restoran1.setArtikli(artikli);


        Set<Porudzbina> porudzbine = new HashSet<>();
        porudzbine.add(porudzbina1);
        porudzbine.add(porudzbina2);
        porudzbinaRepository.saveAll(porudzbine);
        dostavjac1.setPorudzbine(porudzbine);
        kupac1.setPorudzbine(porudzbine);

        artikalRepository.save(artikal1);
        artikalRepository.save(artikal2);

        restoran1.setMenadzer(menadzer1);
        restoranRepository.save(restoran1);*/

        Date datum1 = new Date();


        Admin admin1 = new Admin("Admin1","123");
        adminRepository.save(admin1);

        Dostavljac dostavjac1 = new Dostavljac("Dostavljac1","123","Aleksandar","Ristic","M",datum1,Uloga.DOSTOVLJAC);
        dostavljacRepository.save(dostavjac1);
        Dostavljac dostavjac2 = new Dostavljac("Dostavljac2","123","Nikola","Kojic","M",datum1,Uloga.DOSTOVLJAC);
        dostavljacRepository.save(dostavjac2);
        Kupac kupac1 = new Kupac("Kupac1","123","Lola","Pasic","Z",datum1,Uloga.KUPAC,2);
        kupacRepository.save(kupac1);
        Kupac kupac2 = new Kupac("Kupac2","123","Marija","Kopcic","Z",datum1,Uloga.KUPAC,4);
        kupacRepository.save(kupac2);
        Menadzer menadzer1 = new Menadzer("Menazder1","123","Imenko","Prezimenic","Z",datum1,Uloga.MENADZER,restoranRepository.getByNaziv("Kod Riste"));
        menadzerRepository.save(menadzer1);
        Menadzer menadzer2 = new Menadzer("Menazder2","123","Bobi","Bobic","Z",datum1,Uloga.MENADZER);
        menadzerRepository.save(menadzer2);


        Restoran restoran1 = new Restoran("KodRiste","Narodni");
        restoranRepository.save(restoran1);

        Lokacija lokacija1 = new Lokacija(234L,"BoguIzaNogu",23,14);
        lokacijaRepository.save(lokacija1);

        Porudzbina porudzbina1 = new Porudzbina(datum1,23, Status.OBRADA);
        porudzbinaRepository.save(porudzbina1);
        Porudzbina porudzbina2 = new Porudzbina(datum1,50, Status.CEKA_DOSTAVLJACA);
        porudzbinaRepository.save(porudzbina2);




        Artikal artikal1 = new Artikal("Belo",50,"Mnogo dobro",200);
        artikalRepository.save(artikal1);
        Artikal artikal2 = new Artikal("Mesano",100,"Opa",100);
        artikalRepository.save(artikal2);



        Set<Artikal> artikli = new HashSet<>();
        artikli.add(artikal1);
        artikli.add(artikal2);
        artikalRepository.save(artikal1);
        artikalRepository.save(artikal2);
        restoran1.setArtikli(artikli);
        restoranRepository.save(restoran1);
        restoran1.setMenadzer(menadzer1);
        restoranRepository.save(restoran1);

        artikal1.setPorudzbina(porudzbina1);
        artikal2.setPorudzbina(porudzbina1);

        porudzbina1.setDostavljac(dostavjac1);
        porudzbinaRepository.save(porudzbina1);

        Set<Porudzbina> porudzbine = new HashSet<>();
        porudzbine.add(porudzbina1);
        restoran1.setPorudzbine(porudzbine);

        return true;
    }

}

