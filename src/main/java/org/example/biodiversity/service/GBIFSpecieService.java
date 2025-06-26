package org.example.biodiversity.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.biodiversity.dto.GBIFSpecieDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class GBIFSpecieService {

    public static String readStringFromURL(String requestURL) throws IOException
    {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString()))
        {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

   public GBIFSpecieDto getById(Long id){
       GBIFSpecieDto specieResponseDto = null;
       try{
           String json = readStringFromURL("https://api.gbif.org/v1/species/match?usageKey="+id);
           ObjectMapper objectMapper = new ObjectMapper();
           specieResponseDto = objectMapper.readValue(json, GBIFSpecieDto.class);
       } catch (IOException e){
           e.printStackTrace();
       }
       return specieResponseDto;
   }

    public GBIFSpecieDto getByName(String name){
        GBIFSpecieDto specieResponseDto = null;
        try{
            String json = readStringFromURL("https://api.gbif.org/v1/species/match?name="+name);
            ObjectMapper objectMapper = new ObjectMapper();
            specieResponseDto = objectMapper.readValue(json, GBIFSpecieDto.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        return specieResponseDto;
    }


    /*public SpecieResponseDto get(Long id){ return specieRepository.findById(id).orElseThrow(NotFoundException::new).entityToDto(); }

    public List<SpecieResponseDto> get(){
        return specieRepository.findAll().stream().map(Specie::entityToDto).toList();
    }*/
}
