# Lavomerka Player Replacer — Fabric client mod 1.21.11

Client-only Fabric mod for Minecraft 1.21.11.

## Что делает

- Открывает меню по Right Shift.
- В меню можно включить/выключить замену всех игроков.
- При включении все игроки на клиенте рендерятся как кастомная лавомерка.
- Сервер, хитбоксы, скины и реальные сущности не меняются.

## Сборка

```bash
./gradlew build
```

Готовый `.jar` будет в:

```text
build/libs/
```

## Версии

- Minecraft: 1.21.11
- Fabric Loader: 0.18.1+
- Fabric API: 0.141.4+1.21.11
- Loom: 1.14-SNAPSHOT
- Java: 21

## Примечание

В Minecraft 1.21.11 сильно изменён клиентский рендер. Если Yarn обновится и поменяет имя/сигнатуру `submitModel` или render-state полей, нужно поправить `LavomerkaRenderHelper` и `LivingEntityRendererMixin` под актуальные mappings.
