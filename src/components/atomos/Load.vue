<template>
    <v-progress-linear
        indeterminate
        class="mx-auto text-center"
        color="blue darken-4"
        v-show="load"
    />
</template>
<script>
export default {
    props: {
        load: {
            type: Boolean,
            required: true,
        }
    }
}
</script>