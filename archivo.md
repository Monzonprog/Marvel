# Comentarios

## General
- Se ha usado uan arquitectura basada en el patrón de presentación MVVM + Casos de Uso + Patrón repository.
- Se ha utilizado KOIN como inyector de dependencias.
- Se ha utilizado COIL para cargar las imagenes en los ImageView.
- Se ha utilizado grafo de navegación para la navegación, creando un gestor de navegación al nivel de ViewModel.
- Para la comunicación entre ViewModel y Vista se ha utilizado StateFlow.
- Para los servicios-web se ha hecho uso del sdk de Retrofit 2 con el conversor GSON.
- Todos los datos del API Marvel (Base url, métodos, claves privadas y publicas, etc) se han metido en la clase MarvelAPI.
- Para todo el flujo de hilos se ha utilizado Flow de Coroutines.

##App
- La app se compone de una pantalla Home (listado de heroes) y de una pantalla para visualizar el detalle de cada heroe (clicando sobre cualquier heroe del listado).
- Se ha dejado que se recargue la info en la Home cada vez que se crea el Fragment (al girar la pantalla o al volver desde el detalle).


