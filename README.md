# API RESTful de Grafos

<h3>Esta API possui como objetivo realizar algumas tarefas utilizando grafos. Ela foi desenvolvida para o processo seletivo na vaga de Analista Jr do <a href="https://www.original.com.br/">Banco Original</a>.</h3>

[![Author](https://img.shields.io/badge/author-mrpapaia-AD1256?style=flat-square)](https://github.com/mrpapaia)
[![Languages](https://img.shields.io/github/languages/count/mrpapaia/desafio-banco-original?color=%23AD1256&style=flat-square)](#)
![GitHub stars](https://img.shields.io/github/stars/mrpapaia/desafio-banco-original?style=flat-square)
![GitHub forks](https://img.shields.io/github/forks/mrpapaia/desafio-banco-original?style=flat-square)

## 🔥 Tecnologias Utilizadas

- Java
- Spring Boot
- JPA
- Hibernate
- Banco de dados H2
- JUnit

## 📍 Endpoints

|            | Endpoint | Descrição |
|------------|----------|-----------|
| `POST` | /graph   | Recebe um grafo e salva no banco de dados. [Ver mais detalhes](#Adicionar-grafo) |
| `GET`  | /graph/<_graphId_> | Busca um grafo por id. [Ver mais detalhes](#Buscar-grafo-por-id) |
| `POST` | /routes/from/<_town1_>/to/<_town2_>?maxStops=<_maxStops_> | Encontrar todos caminhos entre dois vértices, podendo definir as paradas máximas. [Ver mais detalhes](#Rota-entre-dois-pontos) |
| `POST` | /routes/<_graphId_> /from/<_town1_>/to/<_town2_>?maxStops=<_maxStops_> | Mesma funcionalidade do anterior, porém será calculado baseado em um grafo salvo. [Ver mais detalhes](#Rota-entre-dois-pontos-de-um-grafo-salvo) |
| `POST` | /distance | Retorna a distância total de um caminho pré-definido entre dois vértices. [Ver mais detalhes](#Distancia-entre-dois-pontos)|
| `POST` | /distance/<_graphId_>  | Mesma funcionalidade do anterior, porém será calculado baseado em um grafo salvo. [Ver mais detalhes](#Distancia-entre-dois-pontos-de-um-grafo-salvo)|
| `POST` | /distance/from/<_town1_>/to/<_town2_> | Calcula o caminho com menor distância entre dois vértices. [Ver mais detalhes](#Caminho-e-distancia-entre-dois-pontos)|
| `POST` | /distance/<_graphId_>/from/<_town1_>/to/<_town2_> | Mesma funcionalidade do anterior, porém será calculado baseado em um grafo salvo. [Ver mais detalhes](#Caminho-e-distancia-entre-dois-pontos-de-um-grafo-salvo) |


**Adicionar grafo**
----

Esse endpoint recebe as arestas de um grafo e as salva no bando de dados H2.

* **URL**

  /graph

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:** 
      ```
     None
      ```
      
   **Optional:**
 
      ```
     None
      ```

* **Data Params**

  ```json
     {
        "data": [
          {
            "source": "A",
            "target": "B",
            "distance": 5
          },
          {
            "source": "B",
            "target": "C",
            "distance": 4
          },
          {
            "source": "C",
            "target": "D",
            "distance": 8
          },
          {
            "source": "D",
            "target": "C",
            "distance": 8
          },
          {
            "source": "D",
            "target": "E",
            "distance": 6
          },
          {
            "source": "A",
            "target": "D",
            "distance": 5
          },
          {
            "source": "C",
            "target": "E",
            "distance": 2
          },
          {
            "source": "E",
            "target": "B",
            "distance": 3
          },
          {
            "source": "A",
            "target": "E",
            "distance": 7
          }
        ]
    }
  ```
* **Success Response:**
  
Esse endpoint retorna o codigo 201 CREATED e um json contendo o grafo salvo com o seu ID.
  * **Code:** 201 <br />
    **Content:** 
    ```json
      {
        "id": 1,
        "data": [
          {
            "source": "A",
            "target": "B",
            "distance": 5
          },
          {
            "source": "B",
            "target": "C",
            "distance": 4
          },
          {
            "source": "C",
            "target": "D",
            "distance": 8
          },
          {
            "source": "D",
            "target": "C",
            "distance": 8
          },
          {
            "source": "D",
            "target": "E",
            "distance": 6
          },
          {
            "source": "A",
            "target": "D",
            "distance": 5
          },
          {
            "source": "C",
            "target": "E",
            "distance": 2
          },
          {
            "source": "E",
            "target": "B",
            "distance": 3
          },
          {
            "source": "A",
            "target": "E",
            "distance": 7
          }
        ]
    }
    ```


* **Notes:**


----
**Buscar grafo por id**
----
  Esse endpoint recebe o um ID do tipo integer.

* **URL**

  /graph/<_graphId_>

* **Method:** 
  

  `GET` 
  
*  **URL Params**

   **Required:** 
      ```
      graphId=[integer]
      ```
      
   **Optional:**
 
      ```
     None
      ```

* **Data Params**
  ```
  None
  ```
* **Success Response:**
  
 Caso exista um grafo salvo com o ID informado no URL, sera retornado o codigo 200 OK e um json com as informações do grafo.

  * **Code:** 200 <br />
    **Content:** 
    ```json
      {
        "id": 1,
        "data": [
          {
            "source": "A",
            "target": "B",
            "distance": 5
          },
          {
            "source": "B",
            "target": "C",
            "distance": 4
          },
          {
            "source": "C",
            "target": "D",
            "distance": 8
          },
          {
            "source": "D",
            "target": "C",
            "distance": 8
          },
          {
            "source": "D",
            "target": "E",
            "distance": 6
          },
          {
            "source": "A",
            "target": "D",
            "distance": 5
          },
          {
            "source": "C",
            "target": "E",
            "distance": 2
          },
          {
            "source": "E",
            "target": "B",
            "distance": 3
          },
          {
            "source": "A",
            "target": "E",
            "distance": 7
          }
        ]
    }
    ```
 * **Error Response:**
 Caso não exista um grafo salvo com o ID informado no URL, sera retornado o codigo 404 NOT FOUND.
  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    
       ```
        None
       ```

* **Notes:**


----
**Rota entre dois pontos**
----
Esse endpoint calcula todas as rotas disponíveis de um ponto de origem para outro de destino, dado um número máximo de paradas. Se não existirem rotas possíveis, o resultado sera uma lista vazia. Se o parâmetro "maxStops" não for definido, o resultado sera um json com todas as rotas possíveis.

* **URL**

  /routes/from/{town1}/to/{town2}

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:**
 
      ```
      town1=[string]
      town2=[string]
      ```   
   **Optional:**
 
      ```
     None
      ```
* **Data Params**
  ```json
     {
        "data": [
          {
            "source": "A",
            "target": "B",
            "distance": 5
          },
          {
            "source": "B",
            "target": "C",
            "distance": 4
          },
          {
            "source": "C",
            "target": "D",
            "distance": 8
          },
          {
            "source": "D",
            "target": "C",
            "distance": 8
          },
          {
            "source": "D",
            "target": "E",
            "distance": 6
          },
          {
            "source": "A",
            "target": "D",
            "distance": 5
          },
          {
            "source": "C",
            "target": "E",
            "distance": 2
          },
          {
            "source": "E",
            "target": "B",
            "distance": 3
          },
          {
            "source": "A",
            "target": "E",
            "distance": 7
          }
        ]
    }
  ```
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** 
    ```json
      {
        "routes": [
          {
            "route": "ABC",
            "stops": 2
          },
          {
            "route": "ADC",
            "stops": 2
          },
          {
            "route": "ADEBC",
            "stops": 4
          },
          {
            "route": "AEBC",
            "stops": 3
          }
        ]
      }
    ```


* **Notes:**

----
**Rota entre dois pontos de um grafo salvo**
----
Esse endpoint faz exatamente o mesmo que o anterior, porém utilizando um grafo salvo anteriormente. Se o grafo não existir, deverá retornar HTTP NOT FOUND.


* **URL**

  /routes/<_graphId_>from/<_town1_>/to/<_town2_>

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:**
 
      ```
      graphId=[integer]
      town1=[string]
      town2=[string]
      ```   
   **Optional:**
 
      ```
     maxStop=[integer]
      ```
* **Data Params**
  ```
  None
  ```
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** 
    ```json
      {
        "routes": [
          {
            "route": "ABC",
            "stops": 2
          },
          {
            "route": "ADC",
            "stops": 2
          },
          {
            "route": "ADEBC",
            "stops": 4
          },
          {
            "route": "AEBC",
            "stops": 3
          }
        ]
      }
    ```
 * **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    



* **Notes:**

---


**Distancia entre dois pontos**
----
Esse endpoint  retorna a distância total de um caminho entre uma lista direcionada e específica de bairros. Caso a lista de bairros esteja vazia ou seja unitária, o resultado sera zero. Se o dado caminho não existir, então o resultado sera -1.
* **URL**

  /distance

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:**
 
      ```
      None
      ```   
   **Optional:**
 
      ```
     None
      ```

* **Data Params**

  ```json
     {
	"path": [
		"A",
		"B",
		"C",
		"D"
	],
	"data": [
        {
          "source": "A",
          "target": "B",
          "distance": 5
        },
        {
          "source": "B",
          "target": "C",
          "distance": 4
        },
        {
          "source": "C",
          "target": "D",
          "distance": 8
        },
        {
          "source": "D",
          "target": "C",
          "distance": 8
        },
        {
          "source": "D",
          "target": "E",
          "distance": 6
        },
        {
          "source": "A",
          "target": "D",
          "distance": 5
        },
        {
          "source": "C",
          "target": "E",
          "distance": 2
        },
        {
          "source": "E",
          "target": "B",
          "distance": 3
        },
        {
          "source": "A",
          "target": "E",
          "distance": 7
        }
      ]
     }
  ```
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** 
    ```json
       {
        "distance": 17
      }
    ```


* **Notes:**

----
**Distancia entre dois pontos de um grafo salvo**
----
Esse endpoint  faz exatamente o mesmo que o anterior, porém utilizando um grafo salvo anteriormente. Se o grafo não existir, retorna HTTP NOT FOUND.


* **URL**

  /distance/<_graphId_>

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:**
 
      `graphId=[integer]`    
  
   **Optional:**
 
      ```
     None
      ```

* **Data Params**
  ```
  None
  ```
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** 
    ```json
       {
        "distance": 17
      }
    ```
    
 * **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    
       ```
        None
       ```

* **Notes:**

----


**Caminho e distancia entre dois pontos**
----
Esse endpoint determina a rota cuja distância seja a mínima possível entre dois bairros. Se os bairros de origem e destino forem iguais, o resultado sera zero. Se não existir rota possível entre os dois bairros, então o sera -1.
* **URL**

  /distance/from/{town1}/to/{town2}

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:**
 
      ```
      town1=[string]
      town2=[string]
      ```   
   **Optional:**
 
      ```
     None
      ```

* **Data Params**
  ```json
     {
        "data": [
          {
            "source": "A",
            "target": "B",
            "distance": 5
          },
          {
            "source": "B",
            "target": "C",
            "distance": 4
          },
          {
            "source": "C",
            "target": "D",
            "distance": 8
          },
          {
            "source": "D",
            "target": "C",
            "distance": 8
          },
          {
            "source": "D",
            "target": "E",
            "distance": 6
          },
          {
            "source": "A",
            "target": "D",
            "distance": 5
          },
          {
            "source": "C",
            "target": "E",
            "distance": 2
          },
          {
            "source": "E",
            "target": "B",
            "distance": 3
          },
          {
            "source": "A",
            "target": "E",
            "distance": 7
          }
        ]
    }
  ```
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** 
    ```json
    {
      "path": [
        "A",
        "B",
        "C"
      ],
      "distance": 8
    }
    ```


* **Notes:**

----
**Caminho e distancia entre dois pontos de um grafo salvo**
----
Esse endpoint  faz exatamente o mesmo que o anterior, porém utilizando um grafo salvo anteriormente. Se o grafo não existir, retorna HTTP NOT FOUND.


* **URL**

  /distance/<_graphId_>from/<_town1_>/to/<_town2_>

* **Method:** 
  

  `POST` 
  
*  **URL Params**

   **Required:**
 
      ```
      graphId=[integer]
      town1=[string]
      town2=[string]
      ```   
 
      
   **Optional:**
 
      ```
     None
      ```
* **Data Params**
  ```
  None
  ```
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** 
    ```json
    {
      "path": [
        "A",
        "B",
        "C"
      ],
      "distance": 8
    }
    ```
 * **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    
       ```
        None
       ```

* **Notes:**

---
