# Assignment 2 - Factory Method and Abstract Factory
 
Author: Ansar Moltabayev, SE-2530.

## Purpose

One console Java application for a logistics company. It combines two creational patterns:

- **Factory Method** creates the transport for a delivery: road (`Truck`) or sea (`Ship`).
- **Abstract Factory** creates a matching family of UI components (`Button` + `Checkbox`) for Windows or macOS.

The delivery mode and the UI platform are chosen independently at startup, so all four combinations work without editing code. UI components only print their platform and type, so macOS components run on any machine.

## Package structure

```
src/com/assignment2/
  logistics/   Part A - Factory Method
    Transport            product interface (deliver)
    Truck, Ship          concrete products
    Logistics            abstract creator (createTransport + shared planDelivery)
    RoadLogistics        concrete creator -> Truck
    SeaLogistics         concrete creator -> Ship
  gui/         Part B - Abstract Factory
    Button, Checkbox     abstract products (paint)
    WindowsButton, WindowsCheckbox, MacOSButton, MacOSCheckbox   concrete products
    GUIFactory           abstract factory (createButton, createCheckbox)
    WindowsFactory, MacOSFactory   concrete factories
  app/         client and startup
    DeliveryApplication  client: gets GUIFactory + Logistics via constructor
    Main                 input validation and selection of creator and factory
    DeliveryMode, UiPlatform   enums for the supported choices
docs/uml/      UML class diagrams (.puml sources and .png images)
scripts/run_checks.sh   compiles and runs all required checks
```






## Verification

`./scripts/run_checks.sh` compiles the project and runs the six required checks plus the missing-input cases. The output of the last run is saved in `docs/verification.txt`.

## Assessed version

The commit to assess is the latest commit on the `main` branch (its hash is given in the report).
