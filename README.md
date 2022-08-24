Консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

Writer(id, name, List<Post> posts)

Post(id, content, List<Tag> tags, PostStatus status)

Tag(id, name)

PostStatus (enum ACTIVE, DELETED)
Описание:

    Реализованы все CRUD операции для каждой из сущностей
    Подход MVC
    Для сборки проекта использовать Maven
    Для взаимодействия с БД - Hibernate
    Для конфигурирования Hibernate - аннотации
    БД реализована с помощью flyway
    Сервисный слой приложения покрыт юнит тестами (junit + mockito)

Технологии: Java, MySQL, Hibernate, Flyway, Maven.
