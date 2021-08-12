<template>
    <v-app>
        <slot name="appbar"></slot>
        
        <v-main class="mt-n15">
            <slot name="main" ></slot>
        </v-main>
    </v-app>
</template>
