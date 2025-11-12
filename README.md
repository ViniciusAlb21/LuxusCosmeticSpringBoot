# 💄 LuxusCosmetic — Sistema de Loja de Cosméticos

## 🧭 Visão Geral
O **LuxusCosmetic** é um sistema completo de e-commerce desenvolvido em **Spring Boot**, que simula o funcionamento de uma loja de cosméticos.  
Ele foi criado para demonstrar modelagem de dados, relacionamentos complexos (1:N, N:N, chaves compostas, chaves estrangeiras como primárias) e operações além do CRUD tradicional, conforme solicitado no **Trabalho Prático – Spring Boot**.

A aplicação permite gerenciar produtos, clientes, pedidos, pagamentos, cupons de desconto e avaliações, oferecendo uma base sólida para sistemas reais de vendas on-line.

---

## 🧩 Descrição do Domínio Modelado
O domínio representa um **sistema de loja virtual de cosméticos**.  
O sistema possibilita:

- Cadastro e listagem de **produtos**, **categorias**, **marcas** e **tags**.  
- Registro de **clientes** e seus **endereços**.  
- Criação e gerenciamento de **pedidos** com itens, cupons e pagamentos.  
- Registro de **avaliações** dos produtos por clientes.  
- Consultas com filtros avançados e **relatórios de vendas e avaliações**.

---

## 🧱 Entidades e Justificativas

| Entidade | Descrição | Tipo de Relação |
|-----------|------------|----------------|
| **Product** | Produtos vendidos na loja (nome, preço, estoque, categoria, marca) | N:1 com Category e Brand; N:N com Tag |
| **Category** | Classificação dos produtos (Perfume, Skincare, Maquiagem, etc.) | 1:N com Product |
| **Brand** | Marca/fabricante do produto | 1:N com Product |
| **Tag** | Palavras-chave de produtos (vegano, natural, etc.) | N:N com Product |
| **Customer** | Clientes cadastrados na loja | 1:N com Order e Address |
| **Address** | Endereços de entrega e cobrança do cliente | N:1 com Customer |
| **Order** | Pedido de compra realizado pelo cliente | N:1 com Customer; 1:N com OrderItem; 1:1 com Payment |
| **OrderItem** | Item de pedido (produto, quantidade, preço) | N:1 com Order e Product (PK composta) |
| **Payment** | Pagamento do pedido | 1:1 com Order (FK = PK) |
| **Review** | Avaliação feita pelo cliente sobre o produto | N:1 com Product e Customer |
| **Coupon** | Cupom de desconto aplicado a pedidos | 1:N com Order |

---

## 🧮 Diagrama Lógico (ER) Simplificado

```
Category (1) ----< (N) Product >---- (N) Tag
       |               |
       |               v
       |          Brand (1)
       |
Customer (1) ----< (N) Order ----< (N) OrderItem >---- (N) Product
       |                    |
       v                    v
Address (N)           Payment (1:1, FK=PK)

Product (1) ----< (N) Review >---- (N) Customer
Order (N) ----> Coupon (1)
```

**Destaques técnicos:**
- `OrderItem` possui **chave primária composta** (`order_id`, `product_id`).
- `Payment` possui **chave estrangeira como chave primária** (`@MapsId`).
- `Product` e `Tag` formam uma relação **Many-to-Many** (`@ManyToMany`).
- Relações 1:N implementadas em `Category→Product`, `Customer→Order`, `Customer→Address`.

---

## 🔗 Descrição das Relações e Operações Adicionais

### Relações
- **1:N / N:1:** Category → Product, Customer → Order, Customer → Address, Brand → Product  
- **N:N:** Product ↔ Tag  
- **1:1 com FK como PK:** Order → Payment  
- **Chave primária composta:** OrderItemId (Order + Product)

### Operações Além do CRUD
- **Cálculos e agregações:** total de vendas, média de avaliação, ticket médio.  
- **Consultas compostas:** busca de produtos com múltiplos filtros (nome, preço, categoria, marca, tags).  
- **Transações:** criação de pedido (`@Transactional`) envolve criação de itens, pagamento e atualização de estoque.  
- **Respostas agregadas:** relatórios combinando dados de várias entidades.

---

## 🌐 Exemplos de Uso — Chamadas de API

### 🔍 Buscar produtos com filtros
```bash
GET /api/products/search?name=batom&category=makeup&minPrice=20&maxPrice=120&tags=vegano
```

### ➕ Criar produto
```bash
POST /api/products
Content-Type: application/json

{
  "name": "Base Líquida Matte",
  "description": "Alta cobertura e longa duração.",
  "price": 59.90,
  "stock": 30,
  "brandId": 1,
  "categoryId": 2
}
```

### 🧾 Criar pedido (com cupom e pagamento)
```bash
POST /api/orders
Content-Type: application/json

{
  "customerId": 1,
  "addressId": 3,
  "items": [
    { "productId": 5, "quantity": 2 },
    { "productId": 8, "quantity": 1 }
  ],
  "couponCode": "DESC10",
  "payment": {
    "type": "CREDIT_CARD",
    "details": { "cardNumber": "4111111111111111", "expiry": "12/26" }
  }
}
```

### 💰 Relatório de vendas por período
```bash
GET /api/reports/sales?from=2025-01-01&to=2025-03-31
```
**Resposta:**
```json
{
  "totalSales": 15230.50,
  "ordersCount": 120,
  "averageTicket": 126.92
}
```

### ⭐ Média de avaliações do produto
```bash
GET /api/products/5/rating
```
**Resposta:**
```json
{ "productId": 5, "averageRating": 4.3, "reviewsCount": 23 }
```

---

## ⚙️ Estrutura do Projeto
```
src/
 ├─ main/
 │   ├─ java/com/web/LuxusCosmetic/
 │   │   ├─ controller/    → Endpoints REST
 │   │   ├─ service/       → Regras de negócio
 │   │   ├─ repository/    → Interfaces JPA
 │   │   └─ domain/        → Entidades
 │   └─ resources/
 │       ├─ application.properties
 │       └─ data.sql (opcional)
 └─ test/                  → Testes automatizados
```

## 🧠 Conclusão
O projeto **LuxusCosmetic** demonstra o domínio completo da modelagem relacional com **Spring Boot e JPA**, incluindo todos os tipos de relações exigidas, operações de negócio transacionais e respostas agregadas.  

---


📄 **Autor:** Vinicius  
📆 **Versão:** 1.0 — ADO 1
