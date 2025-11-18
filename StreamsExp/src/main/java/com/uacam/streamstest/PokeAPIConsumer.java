package com.uacam.streamstest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.FileWriter;
import java.io.IOException;

public class PokeAPIConsumer {
    
    public static void main(String[] args) {
        try {
            String pokemonName = "pikachu";
            String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;
            
            String response = getPokemonData(apiUrl);
            System.out.println("Datos de " + pokemonName + ":");
            System.out.println(response);
            
            if (response != null) {
                System.out.println("Datos de " + pokemonName + " obtenidos correctamente");
                
                // Guardar en archivo JSON
                savePokemonToFile(pokemonName, response);
                
                System.out.println("Contenido obtenido:");
                System.out.println(response);
            } else {
                System.out.println("No se pudieron obtener los datos del Pokémon");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void savePokemonToFile(String pokemonName, String jsonData) {
        String filename = pokemonName + "_data.json";
        
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonData);
            file.flush();
            System.out.println("Datos guardados en: " + filename);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public static String getPokemonData(String apiUrl) {
        StringBuilder response = new StringBuilder();
        
        try {
            // Crear la conexión HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Configurar la solicitud
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            // Verificar código de respuesta
            int responseCode = connection.getResponseCode();
            System.out.println("response code: "+responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
                );
                
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
            } else {
                System.out.println("Error en la solicitud. Código: " + responseCode);
                return null;
            }
            
            connection.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return response.toString();
    }
}