package com.example.demo.controller;

import com.example.demo.dto.KorpaDto;
import com.example.demo.dto.KupacDto;
import com.example.demo.dto.LogInDto;
import com.example.demo.entity.*;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class KupacRestController {
    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KorpaService korpaService;

    @PostMapping(value = "/api/kupac/registracija",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Kupac> saveKupac(@RequestBody Kupac kupac)
    {
        this.kupacService.save(kupac);
        return new ResponseEntity<>(kupac,HttpStatus.OK);
    }

    @PostMapping(value="/api/kupac/prijava",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LogInDto> login(@RequestBody LogInDto loginDto, HttpSession session)
    {
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Kupac logovaniKupac = kupacService.login(loginDto.getUsername(),loginDto.getPassword());

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Korisnik sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("kupac",logovaniKupac);
        return new ResponseEntity<>(loginDto,HttpStatus.OK);
    }

    @PostMapping("api/kupac/odajvi")
    public ResponseEntity Logout(HttpSession session){
        Kupac loggedKupac = (Kupac) session.getAttribute("kupac");

        if (loggedKupac == null)
            return new ResponseEntity("Kupac nije ni prijavljen", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Uspesno odjavljen kupac iz sistema", HttpStatus.OK);
    }

    @GetMapping(value="/api/kupac/profil",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KupacDto> getKupac(HttpSession session){
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");


        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Kupac kupac = kupacService.findOne(logovaniKupac.getUsername());
        KupacDto dto = new KupacDto(kupac);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value="/api/kupac/izmeni",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Kupac> setKupac(HttpSession session, @RequestBody KupacDto kupacDto) {

        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        logovaniKupac.setUsername(kupacDto.getUsername() == null ? logovaniKupac.getUsername() : kupacDto.getUsername());
        logovaniKupac.setPassword(kupacDto.getPassword() == null ? logovaniKupac.getPassword() : kupacDto.getPassword());
        logovaniKupac.setIme(kupacDto.getIme() == null ? logovaniKupac.getIme() : kupacDto.getIme());
        logovaniKupac.setPrezime(kupacDto.getPrezime() == null ? logovaniKupac.getPrezime() : kupacDto.getPrezime());

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(logovaniKupac);
    }
//------------------------------------------------------------------------
    @GetMapping("/api/kupac/pregled-porudzbina")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        String username = logovaniKupac.getUsername();

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = kupacService.pregledajPorudzbine(username);

        return ResponseEntity.ok(porudzbine);
    }


    @PostMapping(value="/api/kupac/restoran/dodaj-u-korpu",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorpaDto> dodajUKorpu(@RequestBody KorpaDto korpaDto, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        if(korpaDto.getKolicina()<=0)
        {
            return new ResponseEntity("Morate uneti jedan ili vise artikala!",HttpStatus.BAD_REQUEST);
        }

        if( korpaDto.getNazivArtikla()==null || korpaDto.getNazivRestorana()==null)
        {
            return new ResponseEntity("Morate uneti podatke!",HttpStatus.BAD_REQUEST);
        }

        korpaService.dodajUKorpu(logovaniKupac,korpaDto);
        return new ResponseEntity<>(korpaDto,HttpStatus.OK);
    }

    @GetMapping(value="/api/kupac/pregled-korpe",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korpa> pregledKorpe(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Korpa korpa = korpaService.pregledajKorpu(logovaniKupac);
        return ResponseEntity.ok(korpa);

    }

    @PostMapping("/api/kupac/poruci")
    public ResponseEntity<Porudzbina> poruciIzRestorana(@RequestParam String nazivRestorana, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Restoran restoran = restoranRepository.getByNaziv(nazivRestorana);
        Porudzbina porudzbina = porudzbinaService.poruci(logovaniKupac,restoran);

        return ResponseEntity.ok(porudzbina);
    }

}
