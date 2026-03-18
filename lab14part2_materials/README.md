# Материалы преподавателя — Лабораторная работа 14 (часть 2)

> **Этот каталог НЕ предназначен для студентов.** Не включайте его в студенческий репозиторий.

## Содержимое

```
teacher_materials/
├── README.md                                  # Этот файл
├── RESULTS.md                                 # Эталонные ответы на все 7 вопросов
├── multiprocessing_examples/
│   ├── 02_matrix_multiply.py                 # TODO 1-2 выполнены
│   ├── 03_pool_matrix.py                     # TODO 3-4 выполнены
│   └── 04_mp_echo_server.py                  # TODO 9 выполнен
└── asyncio_examples/
    ├── 01_sync_vs_async.py                    # TODO 5 выполнен
    ├── 02_echo_server.py                      # TODO 6 выполнен
    └── 03_echo_client.py                      # TODO 7-8 выполнены
```

## Как использовать

### Проверка работ студентов

Сравните код студента с эталонными решениями. Ключевые моменты для оценки:

| TODO | Что проверить | Типичные ошибки студентов |
|---|---|---|
| 1 | Вложенный цикл по i, j; Process с target=element_to_queue | Забывают start() или join() |
| 2 | Замер времени с time.time(), вывод ускорения | Не вызывают parallel_multiply() |
| 3 | Формирование args и вызов pool.starmap() | Путают map() и starmap() |
| 4 | Цикл по [1, 2, 4] с замером времени | Не меняют num_processes |
| 5 | asyncio.gather() с тремя вызовами fetch_data_async | Забывают await перед gather |
| 6 | Последовательность: read → decode → log → write → drain → close | Забывают await writer.drain() или wait_closed() |
| 7 | write → drain → read → print → close → wait_closed | Не закрывают writer |
| 8 | Список messages + gather с распаковкой * | Запускают клиентов последовательно вместо gather |
| 9 | recv → decode → print PID → sendall → close | Забывают conn.close() |

### Проверка RESULTS.md

Используйте `RESULTS.md` в этом каталоге как эталон. Ключевые критерии:

**Вопрос 1** — студент должен заметить, что для 3x3 параллельная версия медленнее, и объяснить накладные расходы на создание процессов.

**Вопрос 2** — студент должен увидеть, что ускорение ограничено количеством ядер CPU. Дальше — конкуренция за процессорное время.

**Вопрос 3** — ключевое слово: **изоляция памяти процессов**, fork создаёт копию адресного пространства.

**Вопрос 4** — ключевое: **перекрытие времени ожидания** (concurrency), а не параллелизм. max(delays) вместо sum(delays).

**Вопрос 5** — порядок вывода **не детерминирован**, но gather() возвращает результаты в порядке передачи.

**Вопрос 6** — `time.sleep()` блокирует event loop, сервер перестаёт обслуживать других клиентов.

**Вопрос 7** — threading = один PID (общее адресное пространство), multiprocessing = разные PID (fork, отдельные процессы ОС).

### Сравнительная таблица — правильные ответы

| Характеристика | threading | multiprocessing | asyncio |
|---|---|---|---|
| PID обработчиков | одинаковый | разный | одинаковый |
| Несколько ядер CPU | нет (GIL) | да | нет |
| Изоляция памяти | нет | да | нет |
| Многозадачность | вытесняющая | вытесняющая | кооперативная |

## Запуск эталонных решений

```bash
# Часть A
python3 multiprocessing_examples/02_matrix_multiply.py
python3 multiprocessing_examples/03_pool_matrix.py

# Часть B (сервер в одном терминале, клиент в другом)
python3 asyncio_examples/02_echo_server.py
python3 asyncio_examples/03_echo_client.py

# Часть B — sync vs async
python3 asyncio_examples/01_sync_vs_async.py

# Часть C (сервер в одном терминале, клиент в другом)
python3 multiprocessing_examples/04_mp_echo_server.py
# В другом терминале:
python3 ../multiprocessing_examples/05_mp_echo_client.py
```
