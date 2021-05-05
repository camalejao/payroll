# Sistema de Folha de Pagamento

<p align="justify">
O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e impostos deduzidos do salário.
</p>

- <p align="justify">Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal durante as horas extras. Eles são pagos toda sexta-feira.</p>

- <p align="justify">Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês (desconsidere feriados). Tais empregados são chamados "assalariados".</p>

- <p align="justify">Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um percentual das vendas que realizam. Eles submetem resultados de vendas que informam a data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas de salário fixo mais as comissões do período.
  
  - Empregados podem escolher o método de pagamento.
  - Podem receber um cheque pelos correios
  - Podem receber um cheque em mãos
  - Podem pedir depósito em conta bancária

</p>

- <p align="justify">Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato). O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo contracheque do empregado. A identificação do empregado no sindicato não é a mesma da identificação no sistema de folha de pagamento.</p>

- <p align="justify">A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o pagamento para cada empregado desde a última vez em que este foi pago.</p>

## Code Smells

### Duplicated Code

- Classes ServiceTax e SaleReport, apesar de semânticamente diferentes, têm exatamente os mesmos atributos: date (LocalDate) e value (Double); além disso, contam com o mesmo método toString(), imprimindo os dois atributos.

- Repetição da estrutura condicional para tratar opções de menu, tanto no menu principal (método main da classe PayrollApp) quanto no menu para editar usuário (método editEmployee da classe EmployeeMenu).

- Repetição da estrutura para tratar se a lista de funcionários está vazia `if (!company.isEmployeeListEmpty())` ao longo do menu principal no método main de PayrollApp.

- Repetição da chamada do método `ConsoleUtils.pressEnterToContinue(input)` para limpar a tela após interações com o menu.

### Long Parameter List

- Chamadas de métodos construtores para as classes Commissioned, Salaried e Hourly, feitas no método editEmployee da classe EmployeeMenu: retira 5 atributos de um objeto Employee para passá-los como parâmetro.

### Long Method

- Declarações de Switch/Case muito extensas para lidar com as requisições do usuário, tanto no menu principal (método main da classe PayrollApp) quanto no menu para editar usuário (método editEmployee da classe EmployeeMenu).

- Método registerNewPaymentSchedule() da classe PaymentsMenu acumula dados de maneira extensa em variáveis locais do tipo String

- Métodos toString, getDividingFactor e checkIfDateIsInSchedule da classe PaymentSchedule apresentam várias decisões lógicas para escolher o comportamento/dados adequados.

- Métodos da classe EmployeeMenu acumulam muitas variáveis locais

### Generative Speculation

- Atributo includesUnionTax da classe Paycheck não é utilizado

- Construtores vazios de várias classes não são utilizados

- Métodos getters e setters de várias classes nunca são utilizados

### Data Class

- A classe Paycheck conta apenas com dados em seus atributos e um método toString, o restante da lógica relacionada é lidado por outras classes

### Feature Envy

- Método getServiceTaxStrings() da classe UnionMember está mais interessado na classe ServiceTax; 

- Método getTimecardStrings() da classe Hourly mais interessado na classe Timecard

- Método getSaleReportStrings() da classe Commissioned mais interessado na classe SaleReport

- Método printPaymentsReports da classe Company se interessa mais na classe PaymentsReports

### Long Class

- Quantidade numerosa de métodos na classe EmployeeMenu

- Classe Company com os métodos getHourlyEmployees(), getCommissionedEmployees(), getSalariedEmployees() e getUnionMemberEmployees(), que agrupam uma linguagem implícita